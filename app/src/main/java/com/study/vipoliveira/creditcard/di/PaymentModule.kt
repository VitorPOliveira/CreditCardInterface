package com.study.vipoliveira.creditcard.di

import com.study.vipoliveira.creditcard.repository.CreditCardRepository
import com.study.vipoliveira.creditcard.repository.CreditCardRepositoryImpl
import com.study.vipoliveira.creditcard.rx.SchedulersFacade
import com.study.vipoliveira.creditcard.service.PaymentService
import com.study.vipoliveira.creditcard.ui.cards.CardsViewModelFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class PaymentModule{
@Provides
    fun provideCreditCardRepository(paymentService: PaymentService) : CreditCardRepository {
        return CreditCardRepositoryImpl(paymentService)
    }

    @Provides
    fun provideCardsViewModelFactory(creditCardRepository: CreditCardRepository,
                                          schedulersFacade: SchedulersFacade,
                                          disposable: CompositeDisposable): CardsViewModelFactory {
        return CardsViewModelFactory(creditCardRepository, schedulersFacade, disposable)
    }
}