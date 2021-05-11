package hu.bme.aut.android.recipy.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

enum class RecipeCategory {
    BREAKFAST, LUNCH, DINNER, DESSERT;

//    companion object {
//        @TypeConverter
//        @JvmStatic
//        fun getByOrdinal(ordinal: Int): RecipeCategory? {
//            var ret: RecipeCategory? = null
//            for (cat in values()) {
//                if (cat.ordinal == ordinal) {
//                    ret = cat
//                    break
//                }
//            }
//            return ret
//        }
//
//        @TypeConverter
//        @JvmStatic
//        fun toInt(category: RecipeCategory): Int {
//            return category.ordinal
//        }
//    }
}

@Entity(tableName = "recipe")
data class Recipe (
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "category") var category: RecipeCategory,
    @ColumnInfo(name = "author") var author: String,
    @ColumnInfo(name = "required_time") var reqiredTime: Int = 0,
    @ColumnInfo(name = "description") var description: String = ""
)