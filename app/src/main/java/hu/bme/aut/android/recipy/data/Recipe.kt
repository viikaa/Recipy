package hu.bme.aut.android.recipy.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

enum class RecipeCategory {
    BREAKFAST, LUNCH, DINNER, DESSERT;
}

@Entity(tableName = "recipe")
data class Recipe (
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "category") var category: RecipeCategory,
    @ColumnInfo(name = "author") var author: String,
    @ColumnInfo(name = "required_time") var reqiredTime: Int = 0,
    @ColumnInfo(name = "description") var description: String = "",
    @ColumnInfo(name = "rating") var rating: Int = 0
)