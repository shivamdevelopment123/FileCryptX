package coder.sentient.filecryptx.viewModel

import androidx.lifecycle.ViewModel
import java.io.File

class FileOperationViewModel:ViewModel() {
    var filesToCopyorCut : List<File>? = null
    var isCutOperation : Boolean = false
}