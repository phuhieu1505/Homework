import 'dart:convert';
import 'package:cooking_recipe/models/recipe.dart';
import 'package:http/http.dart' as http;

class RecipeApi {
  static Future<List<Recipe>> getRecipe() async {
    var uri = Uri.https('yummly2.p.rapidapi.com', '/feeds/list',
        {"limit": "18", "start": "0", "tag": "list.recipe.popular"});

    final response = await http.get(uri, headers: {
      "x-rapidapi-key": "fa9b4556f1msh269474494e824aep1de7e4jsn95ad20525a86",
      "x-rapidapi-host": "yummly2.p.rapidapi.com",
      "useQueryString": "true"
    });

    if (response.statusCode == 200) {
      Map data = jsonDecode(response.body);
      List _temp = [];

      for (var i in data['feed']) {
        var content = i['content'];
        var details = content['details'];
        var ingredientLines = content['ingredientLines'];

        if (details != null && ingredientLines != null) {
          // Thêm cả details và ingredientLines vào danh sách tạm thời
          _temp.add({
            'details': details,
            'ingredientLines': ingredientLines,
          });
        }
      }

      return Recipe.recipesFromSnapshot(_temp);
    } else {
      throw Exception('Failed to load recipes');
    }
  }
}