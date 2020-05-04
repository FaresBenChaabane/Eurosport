package fr.benchaabane.presentationlayer.tools

fun <T> unsafeLazy(initializer: () -> T) = lazy(LazyThreadSafetyMode.NONE, initializer)

