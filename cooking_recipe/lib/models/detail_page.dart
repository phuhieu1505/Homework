import 'package:flutter/material.dart';
import 'recipe.dart';

class DetailPage extends StatelessWidget {
  final Recipe recipe;

  DetailPage({required this.recipe});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(recipe.name, style: TextStyle(color: Colors.black)), // Định dạng màu đen cho tiêu đề
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            // Hình ảnh công thức
            Image.network(recipe.images),
            SizedBox(height: 20),

            // Thông tin thời gian và rating
            Row(
              children: [
                Icon(Icons.schedule, color: Colors.yellow),
                SizedBox(width: 8),
                Text("Cook Time: ${recipe.totalTime}", style: TextStyle(color: Colors.black)), // Định dạng màu đen
                Spacer(),
                Icon(Icons.star, color: Colors.yellow),
                SizedBox(width: 8),
                Text(recipe.rating.toString(), style: TextStyle(color: Colors.black)), // Định dạng màu đen
              ],
            ),
            SizedBox(height: 20),

            // Tiêu đề danh sách nguyên liệu
            Text(
              "Ingredients",
              style: TextStyle(fontSize: 22, fontWeight: FontWeight.bold, color: Colors.black), // Định dạng màu đen
            ),
            // SizedBox(height: 10),
            //
            // // Hiển thị wholeLine cho từng nguyên liệu
            // for (var ingredient in recipe.ingredientLines)
            //   Text(ingredient.wholeLine, style: TextStyle(color: Colors.black)), // Định dạng màu đen

            SizedBox(height: 20),

            // Danh sách các nguyên liệu
            Expanded(
              child: recipe.ingredientLines.isNotEmpty
                  ? ListView.builder(
                itemCount: recipe.ingredientLines.length,
                itemBuilder: (context, index) {
                  final ingredient = recipe.ingredientLines[index];
                  return ListTile(
                    title: Text(ingredient.wholeLine, style: TextStyle(color: Colors.black)), // Định dạng màu đen
                    subtitle: Text(
                        "${ingredient.category}, ${ingredient.quantity} ${ingredient.unit}", style: TextStyle(color: Colors.black)), // Định dạng màu đen
                    trailing: Text(ingredient.remainder, style: TextStyle(color: Colors.black)), // Định dạng màu đen
                  );
                },
              )
                  : Center(child: Text("No ingredients found")),
            ),
          ],
        ),
      ),
    );
  }
}