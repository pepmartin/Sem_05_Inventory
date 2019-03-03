package pe.edu.cibertec.inventory;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Vista
    RecyclerView rvProduct;

    //Modelo : informacion a mostrar
    ArrayList<Product> items;

    //Adaptador
    AdapterProduct adapterProduct;

    //Identiicador-> Estoy mandando un identificador como un ticket
    final static int REQUEST_CODE_MAIN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         //Enlaza variable con su vista a partir de id
        rvProduct = findViewById(R.id.rvProduct);

        //Asigna espacion en memoria
        items = new ArrayList<>();

        //Carga la información a mostrar
        loadItems();

        //Inicializo el adaptador}
        adapterProduct = new AdapterProduct(items);
        rvProduct.setAdapter(adapterProduct);
        //Como lo muestra el Layout
        rvProduct.setLayoutManager(new LinearLayoutManager(this));

    }

    private void loadItems() {
        // Instancia un objeto de la clase Product
        Product productLaptop = new Product("Latop","Marca Toshiba",2);

        // Agrega un objeto creado a la lista
        items.add(productLaptop);

        Product productMouse = new Product("Mouse","Modelo Genius",122);

        items.add(productMouse);

        Product productCel = new Product("Celular","Moto G",9);

        items.add(productCel);

        Product productTecl = new Product("Teclado","Genius G",12);

        items.add(productTecl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this,ProductActivity.class);
        //1. SECUENCIA FOR OBTENER RESULTADO ENTRE PANTALLAS
        startActivityForResult(intent,REQUEST_CODE_MAIN);
        return true;
    }

    //3. OBTIENE EL RESULTADO
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //VALIDOS QUE EL PEDIDO LO HEOS REALIZADO
        if  (requestCode == REQUEST_CODE_MAIN && resultCode == RESULT_OK) {
            String name = data.getStringExtra("product_name");
            String description = data.getStringExtra("product_description");
            int quantity = data.getIntExtra("product_cantidad",0);

            Product product = new Product(name,description,quantity);
            items.add(product);
            adapterProduct.notifyDataSetChanged();
        }
    }
}
