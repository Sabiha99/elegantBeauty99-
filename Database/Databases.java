package com.companyname.elegantbeauty.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.companyname.elegantbeauty.Model.Favourites;
import com.companyname.elegantbeauty.Model.Order;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class Databases extends SQLiteAssetHelper {
    private static final String DbName = "ElegantBeautyDB.db";
    private static final int DbVer = 1;

    public Databases(Context context) {
        super(context, DbName, null, DbVer);
    }


    public List<Order> getCarts() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"ProductId", "ProductName", "Quantity", "Price", "Discount"};
        String sqlTable = "OrderDetails";
        qb.setTables(sqlTable);

        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);
        final List<Order> result = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {

                result.add(new Order(cursor.getString(cursor.getColumnIndex("ProductId")),
                        cursor.getString(cursor.getColumnIndex("ProductName")),
                        cursor.getString(cursor.getColumnIndex("Quantity")),
                        cursor.getString(cursor.getColumnIndex("Price")),
                        cursor.getString(cursor.getColumnIndex("Discount"))

                ));
            } while (cursor.moveToNext());
        }
        return result;

    }
    /////////////////////////***************FAVOURITE********************///////////////

    public void addToFavourite(Favourites product) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO Favourites(" +
                        "BrandId,UserPhone,ProductName,ProductImage,ProductDescription,ProductPrice,ProductBrandNo,ProductDiscount)" +
                        "VALUES('%s','%s','%s','%s','%s','%s','%s','%s');",
                product.getBrandId(),
                product.getUserPhone(),
                product.getProductName(),
                product.getProductImage(),
                product.getProductDescription(),
                product.getProductPrice(),
                product.getProductBrandNo(),
                product.getProductDiscount()


        );
        db.execSQL(query);

    }

    public void removeFromFavourite(String brandId, String userPhone) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM Favourites WHERE BrandId='%s' and UserPhone='%s';", brandId, userPhone);
        db.execSQL(query);

    }

    public void cleanFromFavourite() {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM Favourites");
        db.execSQL(query);

    }

    public boolean ifFavourite(String brandId, String userPhone) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("SELECT * FROM Favourites WHERE BrandId='%s' and UserPhone='%s';", brandId, userPhone);
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        return true;

    }

    ////////////////////***********Add And Remove Order***************///////////////////////////
    public void addToCart(Order order) {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO OrderDetails(ProductId,ProductName,Quantity,Price,Discount)VALUES('%s','%s','%s','%s','%s');",
                order.getProductId(),
                order.getProductName(),
                order.getQuantity(),
                order.getPrice(),
                order.getDiscount());
        db.execSQL(query);
    }

    public void removeFromCart() {
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("DELETE FROM OrderDetails");
        db.execSQL(query);
    }

    public List<Favourites> getFavorites(String userPhone) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"BrandId", "UserPhone", "ProductName", "ProductImage", "ProductPrice", "ProductDescription", "ProductDiscount", "ProductBrandNo"};
        String sqlTable = "Favourites";
        qb.setTables(sqlTable);

        Cursor cursor = qb.query(db, sqlSelect, "UserPhone=?", new String[]{userPhone}, null, null, null, null);
        final List<Favourites> result = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {

                result.add(new Favourites(cursor.getString(cursor.getColumnIndex("BrandId")),
                        cursor.getString(cursor.getColumnIndex("UserPhone")),
                        cursor.getString(cursor.getColumnIndex("ProductName")),
                        cursor.getString(cursor.getColumnIndex("ProductImage")),
                        cursor.getString(cursor.getColumnIndex("ProductDescription")),
                        cursor.getString(cursor.getColumnIndex("ProductPrice")),
                        cursor.getString(cursor.getColumnIndex("ProductDiscount")),
                        cursor.getString(cursor.getColumnIndex("ProductBrandNo"))


                ));
            } while (cursor.moveToNext());
        }
        return result;

    }
}







