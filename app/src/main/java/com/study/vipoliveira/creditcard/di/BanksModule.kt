package com.study.vipoliveira.creditcard.di

import com.study.vipoliveira.creditcard.repository.BankRepository
import com.study.vipoliveira.creditcard.repository.BankRepositoryImpl
import com.study.vipoliveira.creditcard.rx.SchedulersFacade
import com.study.vipoliveira.creditcard.service.PaymentService
import com.study.vipoliveira.creditcard.ui.bank.BanksViewModelFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class BanksModule {
    @Provides
    fun provideBankRepository(paymentService: PaymentService) : BankRepository {
        return BankRepositoryImpl(paymentService)
    }

    @Provides
    fun provideBanksViewModelFactory(bankRepository: BankRepository,
                                    schedulersFacade: SchedulersFacade,
                                    disposable: CompositeDisposable): BanksViewModelFactory {
        return BanksViewModelFactory(bankRepository, schedulersFacade, disposable)
    }
}