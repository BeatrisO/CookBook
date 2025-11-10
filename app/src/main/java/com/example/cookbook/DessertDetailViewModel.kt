import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cookbook.data.MealDetail
import com.example.cookbook.data.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DessertDetailViewModel : ViewModel() {

    private val _dessertDetail = MutableStateFlow<MealDetail?>(null)
    val dessertDetail: StateFlow<MealDetail?> = _dessertDetail

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _hasError = MutableStateFlow(false)
    val hasError: StateFlow<Boolean> = _hasError

    fun fetchDessertDetail(mealId: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val response = RetrofitInstance.api.getMealDetail(mealId)
                _dessertDetail.value = response.meals.firstOrNull()
            } catch (e: Exception) {
                _hasError.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }
}
