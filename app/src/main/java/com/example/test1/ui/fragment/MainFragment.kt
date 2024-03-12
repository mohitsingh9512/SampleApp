package com.example.test1.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test1.R
import com.example.test1.di.factory.ViewModelProviderFactory
import com.example.test1.ui.adapter.CatBreedInterface
import com.example.test1.ui.adapter.MainAdapter
import com.example.test1.ui.base.BaseFragment
import com.example.listcomponent.datamodel.BaseDataModel
import com.example.test1.ui.viewholder.uimodel.CatBreedDataModel
import com.example.test1.viewmodel.MainUiState
import com.example.test1.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainFragment : BaseFragment(), CatBreedInterface {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    lateinit var mainViewModel: MainViewModel

    private var recyclerView : RecyclerView? = null
    private var progress : ProgressBar? = null
    private var mainAdapter : MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel = ViewModelProvider(this, viewModelProviderFactory)[MainViewModel::class.java]
        setUpViews(view)
        setUpObserver()
        //fetchPageData()
    }

    private fun setUpViews(view: View) {
        recyclerView = view.findViewById(R.id.main_rv)
        progress = view.findViewById(R.id.progress)
        setUpAdapter()
    }

    private fun setUpAdapter() {
        recyclerView?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            mainAdapter = MainAdapter(this@MainFragment)
            adapter = mainAdapter
        }
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                launch {
                    mainViewModel.uiState.collect {
                        handleState(it)
                    }
                }
            }
        }
    }

    private fun handleState(it: MainUiState){
        when(it){
            is MainUiState.Error -> handleError(it.errorCode, it.userMessage)
            is MainUiState.Loading -> loadingState(it.isLoading)
            is MainUiState.SuccessItems -> handleSuccess(it.items)
            is MainUiState.None -> {}
        }
    }

    private fun handleSuccess(items: List<BaseDataModel>) {
        loadingState(false)
        if(items.isNotEmpty())
            mainAdapter?.submitList(items)
    }

    private fun handleError(errorCode: Int?, errorMessage: String?) {
        loadingState(false)
        if(errorCode != null) Toast.makeText(context, "Error ${errorCode} $errorMessage", Toast.LENGTH_SHORT).show()
    }

    private fun loadingState(isLoading: Boolean) {
        if(isLoading){
            progress?.visibility = View.VISIBLE
        }else {
            progress?.visibility = View.GONE
        }
    }

    private fun fetchPageData() {
        mainViewModel.getBreeds()
    }

    companion object {
        fun newInstance(bundle: Bundle) : MainFragment {
            return MainFragment().apply {
                arguments = bundle
            }
        }
    }

    override fun onBreedClick(catBreedDataModel: CatBreedDataModel) {

    }
}