package com.example.test1.di.component

import com.example.test1.di.modules.ActivityModule
import com.example.test1.di.modules.CoroutineModule
import com.example.test1.di.modules.RepositoryModule
import com.example.test1.di.modules.ViewModelModule
import com.example.test1.ui.activity.MainActivity
import com.example.test1.ui.fragment.MainFragment
import dagger.Subcomponent

@Subcomponent(modules = [ViewModelModule::class, ActivityModule::class, CoroutineModule::class, RepositoryModule::class])
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)

}