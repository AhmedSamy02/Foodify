import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.foodify.presentation.components.ValidatedOutlinedTextField
import com.example.foodify.presentation.screens.add_recipe.components.SelectorCard
import com.example.foodify.presentation.screens.add_recipe.components.SelectorCardLabel
import javax.xml.validation.Validator

@Composable
fun AddRecipeTime(
    onHourChange: (String) -> Unit,
    onMinuteChange: (String) -> Unit,
    hourValidator: (String) -> String?,
    minuteValidator: (String) -> String?,
) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        SelectorCard {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 36.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                ValidatedOutlinedTextField(
                    onHourChange,
                    hourValidator,
                    "",
                    modifier = Modifier.weight(1f),
                    keyboardType = KeyboardType.Number,
                    suffixText = "h"
                )
                ValidatedOutlinedTextField(
                    onMinuteChange,
                    minuteValidator,
                    "",
                    modifier = Modifier.weight(1f),
                    keyboardType = KeyboardType.Number,
                    suffixText = "m"
                )
            }
        }
        SelectorCardLabel("Cook Time")
    }
}