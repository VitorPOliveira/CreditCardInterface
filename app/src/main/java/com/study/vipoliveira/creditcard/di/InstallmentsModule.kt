package com.study.vipoliveira.creditcard.di


import com.study.vipoliveira.creditcard.repository.InstallmentsRepository
import com.study.vipoliveira.creditcard.repository.InstallmentsRepositoryImpl
import com.study.vipoliveira.creditcard.rx.SchedulersFacade
import com.study.vipoliveira.creditcard.service.PaymentService
import com.study.vipoliveira.creditcard.ui.installments.InstallmentsViewModelFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class InstallmentsModule {
    @Provides
    fun provideInstallmentsRepository(paymentService: PaymentService) : InstallmentsRepository {
        return InstallmentsRepositoryImpl(paymentService)
    }

    @Provides
    fun provideInstallmentsViewModelFactory(installmentsRepository: InstallmentsRepository,
                                     schedulersFacade: SchedulersFacade,
                                     disposable: CompositeDisposable): InstallmentsViewModelFactory {
        return InstallmentsViewModelFactory(installmentsRepository, schedulersFacade, disposable)
    }
}