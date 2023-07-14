package UAS2D;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Conn {
    public void displayData() throws IOException {
        ConnURI koneksiSaya = new ConnURI();
        URL myAddres = koneksiSaya.buildURL("https://dummyjson.com/products/search?q=Laptop");
        String response = koneksiSaya.getResponseFromHttpUrl(myAddres);
        assert response != null;

        JSONObject responseJSON = new JSONObject(response);
        JSONArray productsArray = responseJSON.getJSONArray("products");

        ArrayList<ResponseModel> responseModel = new ArrayList<>();

        for (int i = 0; i < productsArray.length(); i++) {
            JSONObject productObject = productsArray.getJSONObject(i);
            ResponseModel resModel = new ResponseModel();
            resModel.setId(productObject.getInt("id"));
            resModel.setTitle(productObject.getString("title"));
            resModel.setDescription(productObject.getString("description"));
            resModel.setPrice(productObject.getInt("price"));
            resModel.setDiscountPercentage(productObject.getDouble("discountPercentage"));
            resModel.setRating(productObject.getFloat("rating"));
            resModel.setStock(productObject.getInt("stock"));
            resModel.setBrand(productObject.getString("brand"));
            resModel.setCategory(productObject.getString("category"));
            resModel.setThumbnail(productObject.getString("thumbnail"));
            responseModel.add(resModel);
            System.out.println("\n========================================");
            System.out.println("Id : " + resModel.getId());
            System.out.println("Title : " + resModel.getTitle());
            System.out.println("Description : " + resModel.getDescription());
            System.out.println("Price : " + resModel.getPrice());
            System.out.println("Discount Percentage : " + resModel.getDiscountPercentage());
            System.out.println("Rating : " + resModel.getRating());
            System.out.println("Stock : " + resModel.getStock());
            System.out.println("Brand : " + resModel.getBrand());
            System.out.println("Category : " + resModel.getCategory());
            System.out.println("Thumbnail : " + resModel.getThumbnail());
        }
        for (int i = 0; i < responseModel.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < responseModel.size(); j++) {
                if (responseModel.get(j).getRating() < responseModel.get(minIndex).getRating()) {
                    minIndex = j;
                }
            }
            ResponseModel temp = responseModel.get(i);
            responseModel.set(i, responseModel.get(minIndex));
            responseModel.set(minIndex, temp);
        }
        Scanner inputUser = new Scanner(System.in);
        System.out.print("\nCari Rating: ");
        float rating1 = inputUser.nextFloat();
        boolean found = false;
        for (ResponseModel resModel : responseModel) {
            if (resModel.getRating() == rating1) {
                System.out.println("\n== MENAPILKAN RATING " + rating1 + " ==");
                System.out.println("Id : " + resModel.getId());
                System.out.println("Title : " + resModel.getTitle());
                System.out.println("Description : " + resModel.getDescription());
                System.out.println("Price : " + resModel.getPrice());
                System.out.println("Discount Percentage : " + resModel.getDiscountPercentage());
                System.out.println("Rating : " + resModel.getRating());
                System.out.println("Stock : " + resModel.getStock());
                System.out.println("Brand : " + resModel.getBrand());
                System.out.println("Category : " + resModel.getCategory());
                System.out.println("Thumbnail : " + resModel.getThumbnail());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Tidak ada produk dengan rating " + rating1);
        }
    }
    public static void main (String[]args) throws IOException {
        Conn conn = new Conn();
        conn.displayData();
    }
}