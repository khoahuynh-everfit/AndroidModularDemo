package com.example.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.model.error.EverfitError
import com.google.gson.JsonParser
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

open class BaseViewModel : ViewModel() {

    private val _errorLiveData: MutableLiveData<EverfitError> = MutableLiveData()
    val errorLiveData: LiveData<EverfitError>
        get() = _errorLiveData

    private val _loadingDialog: MutableLiveData<Boolean> = MutableLiveData()
    val loadingDialog: LiveData<Boolean>
        get() = _loadingDialog

    val disposables = CompositeDisposable()

    protected fun safeExecute(execution: Completable): Completable {
        return execution.subscribeOn(Schedulers.io())
    }

    protected fun Completable.safeSubscribe(
        onSuccess: () -> Unit,
        onError: ((EverfitError) -> Unit)? = null,
        showLoading: Boolean = true
    ): Disposable {
        return this.observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _loadingDialog.postValue(showLoading)
            }
            .doOnComplete {
                _loadingDialog.postValue(false)
            }
            .doOnError {
                _loadingDialog.postValue(false)
            }
            .doOnDispose {
                _loadingDialog.postValue(false)
            }
            .subscribe({
                onSuccess.invoke()
            }, { throwable ->
                val everfitError = parseThrowable(throwable)
                onError?.invoke(everfitError) ?: run {
                    _errorLiveData.postValue(everfitError)
                }
            }).apply { disposables.add(this) }
    }

    protected fun <T> safeExecute(execution: Single<T>): Single<T> {
        return execution
            .subscribeOn(Schedulers.io())
    }

    protected fun <T> Single<T>.safeSubscribe(
        onSuccess: (T) -> Unit,
        onError: ((EverfitError) -> Unit)? = null,
        showLoading: Boolean = true
    ): Disposable {
        return this.observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                _loadingDialog.postValue(showLoading)
            }
            .doOnSuccess {
                _loadingDialog.postValue(false)
            }
            .doOnError {
                _loadingDialog.postValue(false)
            }
            .doOnDispose {
                _loadingDialog.postValue(false)
            }
            .subscribe({
                onSuccess.invoke(it)
            }, { throwable ->
                val everfitError = parseThrowable(throwable)
                onError?.invoke(everfitError) ?: run {
                    _errorLiveData.postValue(everfitError)
                }
            }).apply { disposables.add(this) }
    }

    private fun parseThrowable(throwable: Throwable): EverfitError {
        val errorMessageDefault = "Unknown error."
        val message = when (throwable) {
            is HttpException -> {
                throwable.response()?.errorBody()?.string()?.let { body ->
                    try {
                        JsonParser().parse(body).asJsonObject["message"]?.asString
                            ?: errorMessageDefault
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                        errorMessageDefault
                    }
                } ?: errorMessageDefault
            }
            else -> errorMessageDefault
        }
        return EverfitError(
            errorTitle = "Error",
            errorMessage = message
        )
    }

}