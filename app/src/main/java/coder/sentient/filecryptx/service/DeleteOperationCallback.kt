package coder.sentient.filecryptx.service

interface DeleteOperationCallback {
    fun onSuccess(deletedFiles: List<String>)
    fun onFailure(errorMessage: String)
}