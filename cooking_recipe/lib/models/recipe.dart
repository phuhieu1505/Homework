class IngredientLine {
  final String category;
  final String unit;
  final String ingredient;
  final String remainder;
  final double quantity;
  final String wholeLine;

  IngredientLine({
    required this.category,
    required this.unit,
    required this.ingredient,
    required this.remainder,
    required this.quantity,
    required this.wholeLine,
  });

  // Factory constructor chuyển JSON thành IngredientLine object
  factory IngredientLine.fromJson(Map<String, dynamic> json) {
    return IngredientLine(
      category: json['category'] ?? 'N/A',
      unit: json['unit'] ?? 'N/A',
      ingredient: json['ingredient'] ?? 'Unknown',
      remainder: json['remainder'] ?? '',
      quantity: (json['quantity'] ?? 0).toDouble(),
      wholeLine: json['wholeLine'] ?? '',
    );
  }
}

class Recipe {
  final String name;
  final String images;
  final double rating;
  final String totalTime;
  final List<IngredientLine> ingredientLines;

  Recipe({
    required this.name,
    required this.images,
    required this.rating,
    required this.totalTime,
    required this.ingredientLines,
  });

  factory Recipe.fromJson(Map<String, dynamic> json) {
    var ingredientsJson = json['ingredientLines'] as List<dynamic>?;
    var ingredients = ingredientsJson != null
        ? ingredientsJson.map((item) => IngredientLine.fromJson(item)).toList()
        : <IngredientLine>[];

    return Recipe(
      name: json['details']['name'] ?? 'Unknown',
      images: json['details']['images'][0]['hostedLargeUrl'] ?? '',
      rating: (json['details']['rating'] ?? 0).toDouble(),
      totalTime: json['details']['totalTime'] ?? 'N/A',
      ingredientLines: ingredients,
    );
  }

  static List<String> getWholeLines(List<Recipe> recipes) {
    return recipes
        .expand((recipe) => recipe.ingredientLines.map((ingredient) => ingredient.wholeLine))
        .toList();
  }

  static List<Recipe> recipesFromSnapshot(List snapshot) {
    return snapshot.map((data) => Recipe.fromJson(data)).toList();
  }

  @override
  String toString() {
    return 'Recipe {name: $name, ingredients: $ingredientLines}';
  }
}