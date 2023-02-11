package com.odogwudev.example.network_implementation.utils.oauth

import com.odogwudev.example.network_implementation.utils.oauth.IsSessionExpiredException as DeprecatedIsSessionExpiredException
import com.halcyonmobile.oauth.dependencies.IsSessionExpiredException

@Deprecated("Only needed as long as com.halcyonmobile.oauth.IsSessionExpiredException] is kept.", level = DeprecationLevel.WARNING)
internal class DeprecatedIsSessionExpiredExceptionAdapter(
    private val delegate: DeprecatedIsSessionExpiredException
) : IsSessionExpiredException {
    override fun invoke(throwable: Throwable): Boolean {
        val httpException = throwable.causeHttpException ?: return false
        return delegate.invoke(httpException)
    }
}
