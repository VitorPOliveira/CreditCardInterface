package com.study.vipoliveira.creditcard.di

import com.study.vipoliveira.creditcard.ui.bank.BanksActivity
import com.study.vipoliveira.creditcard.ui.cards.CardsActivity
import com.study.vipoliveira.creditcard.ui.installments.InstallmentsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = [(PaymentModule::class)])
    abstract fun bindCardsActivity(): CardsActivity

    @ContributesAndroidInjector(modules = [(BanksModule::class)])
    abstract fun bindBanksActivity(): BanksActivity

    @ContributesAndroidInjector(modules = [(InstallmentsModule::class)])
    abstract fun bindInstallmentsActivity(): InstallmentsActivity
}