package com.rcamargo15.icoffeeapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.rcamargo15.icoffeeapp.R;
import com.rcamargo15.icoffeeapp.models.Order;
import com.rcamargo15.icoffeeapp.models.Product;
import com.rcamargo15.icoffeeapp.models.enums.ProductsEnum;
import com.rcamargo15.icoffeeapp.repositories.ProductRepository;
import com.rcamargo15.icoffeeapp.util.AppUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class OrderScreen extends AppCompatActivity {

    private ProductRepository repository = new ProductRepository();

    private Integer orderId = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_screen);
        AppUtils.esconderBarrasTarefas(this);
        setScreenButtons();
        Intent intent = getIntent();
        Optional<Order> clientOrderFromConfirmation = Optional.ofNullable(intent.getSerializableExtra("clientOrder", Order.class));
        Optional<HashMap<String, Integer>> productQuantitiesFromConfirmation = Optional.ofNullable((HashMap<String, Integer>) intent.getSerializableExtra("productQuantities"));
        String clientName = intent.getStringExtra("clientName");
        if(clientOrderFromConfirmation.isPresent() && productQuantitiesFromConfirmation.isPresent()){
            setClientOrderAlreadyMade(productQuantitiesFromConfirmation.get());
        }
        verifyProductQuantities();
        setButtonsBehavior(clientName);

        buttonSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(OrderScreen.this, OrderCanceledScreen.class);
                startActivity(intent2);
            }
        });

        buttonConfirmarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order clientOrder = new Order();
                if(clientOrderFromConfirmation.isPresent()){
                    finalizarPedido(clientOrderFromConfirmation.get().getClientName(), clientOrder);
                } else finalizarPedido(clientName, clientOrder);
            }
        });
    }

    private void setScreenButtons() {
        //Pingado
        quantityPingado = findViewById(R.id.quantityPingado);
        buttonAddPingado = findViewById(R.id.buttonAddPingado);
        buttonRemovePingado = findViewById(R.id.buttonRmPingado);

        //Cafe com Leite
        quantityCafeComLeite = findViewById(R.id.quantityPCafeLeite);
        buttonAddCafeLeite = findViewById(R.id.buttonAddCafeLeite);
        buttonRemoveCafeLeite = findViewById(R.id.buttonRmCafeLeite);

        //Cafe com Leite e Creme
        quantityCafeComLeiteECreme = findViewById(R.id.quantityCafeLeiteCreme);
        buttonAddCafeLeiteCreme = findViewById(R.id.buttonAddCafeLeiteCreme);
        buttonRemoveCafeLeiteCreme = findViewById(R.id.buttonRmCafeLeiteCreme);

        //Cappuccino
        quantityCappuccino = findViewById(R.id.quantityCappuccino);
        buttonAddCappuccino = findViewById(R.id.buttonAddCappuccino);
        buttonRemoveCappuccino = findViewById(R.id.buttonRmCappuccino);

        //Espresso
        quantityEspresso = findViewById(R.id.quantityEspresso);
        buttonAddEspresso = findViewById(R.id.buttonAddEspresso);
        buttonRemoveEspresso = findViewById(R.id.buttonRmEspresso);

        //Cookies
        quantityCookies = findViewById(R.id.quantityCookies);
        buttonAddCookies = findViewById(R.id.buttonAddCookies);
        buttonRemoveCookies = findViewById(R.id.buttonRmCookies);

        //Croissant
        quantityCroissant = findViewById(R.id.quantityCroissant);
        buttonAddCroissant = findViewById(R.id.buttonAddCroissant);
        buttonRemoveCroissant = findViewById(R.id.buttonRmCroissant);

        //Bolo de Fuba
        quantityBoloFuba = findViewById(R.id.quantityBoloFuba);
        buttonAddBoloFuba = findViewById(R.id.buttonAddBoloFuba);
        buttonRemoveBoloFuba = findViewById(R.id.buttonRmBoloFuba);

        //Bolo Floresta Negra
        quantityDarkForestCake = findViewById(R.id.quantityBoloDarkForest);
        buttonAddDarkForestCake = findViewById(R.id.buttonAddBoloDarkForest);
        buttonRemoveDarkForestCake = findViewById(R.id.buttonRmBoloDarkForest);

        //Bolo de Morango
        quantityStrawberryCake = findViewById(R.id.quantityBoloMorango);
        buttonAddStrawberryCake = findViewById(R.id.buttonAddBoloMorango);
        buttonRemoveStrawberryCake = findViewById(R.id.buttonRmBoloMorango);

        //Botoes auxiliares
        buttonSair = findViewById(R.id.buttonSairPedido);
        buttonConfirmarPedido = findViewById(R.id.buttonConfirmarPedido);

        quantityPingado.setText("0");
        quantityCafeComLeite.setText("0");
        quantityCafeComLeiteECreme.setText("0");
        quantityCappuccino.setText("0");
        quantityEspresso.setText("0");
        quantityCookies.setText("0");
        quantityCroissant.setText("0");
        quantityBoloFuba.setText("0");
        quantityDarkForestCake.setText("0");
        quantityStrawberryCake.setText("0");
    }

    private void verifyProductQuantities() {
        if(Integer.parseInt(quantityPingado.getText().toString()) <= 0 || quantityPingado.getText().toString().equals("")){
            quantityPingado.setText("0");
        }
        if(Integer.parseInt(quantityCafeComLeite.getText().toString()) <= 0 || quantityCafeComLeite.getText().toString().equals("")){
            quantityCafeComLeite.setText("0");
        }
        if(Integer.parseInt(quantityCafeComLeiteECreme.getText().toString()) <= 0 || quantityCafeComLeiteECreme.getText().toString().equals("")){
            quantityCafeComLeiteECreme.setText("0");
        }
        if(Integer.parseInt(quantityCappuccino.getText().toString()) <= 0 || quantityCappuccino.getText().toString().equals("")){
            quantityCappuccino.setText("0");
        }
        if(Integer.parseInt(quantityEspresso.getText().toString()) <= 0 || quantityEspresso.getText().toString().equals("")){
            quantityEspresso.setText("0");
        }
        if(Integer.parseInt(quantityCookies.getText().toString()) <= 0 || quantityCookies.getText().toString().equals("")){
            quantityCookies.setText("0");
        }
        if(Integer.parseInt(quantityCroissant.getText().toString()) <= 0 || quantityCroissant.getText().toString().equals("")){
            quantityCroissant.setText("0");
        }
        if(Integer.parseInt(quantityBoloFuba.getText().toString()) <= 0 || quantityBoloFuba.getText().toString().equals("")){
            quantityBoloFuba.setText("0");
        }
        if(Integer.parseInt(quantityDarkForestCake.getText().toString()) <= 0 || quantityDarkForestCake.getText().toString().equals("")){
            quantityDarkForestCake.setText("0");
        }
        if(Integer.parseInt(quantityStrawberryCake.getText().toString()) <= 0 || quantityStrawberryCake.getText().toString().equals("")){
            quantityStrawberryCake.setText("0");
        }
    }



    private void setClientOrderAlreadyMade(HashMap<String, Integer> productQuantities) {
        productQuantities.forEach((k, v) ->{
            switch (k){
                case "qtdPingado":
                    quantityPingado.setText(String.valueOf(v));
                    break;
                case "qtdCafeComLeite":
                    quantityCafeComLeite.setText(String.valueOf(v));
                    break;
                case "qtdCafeComLeiteECreme":
                    quantityCafeComLeiteECreme.setText(String.valueOf(v));
                    break;
                case "qtdCappuccino":
                    quantityCappuccino.setText(String.valueOf(v));
                    break;
                case "qtdEspresso":
                    quantityEspresso.setText(String.valueOf(v));
                    break;
                case "qtdCookies":
                    quantityCookies.setText(String.valueOf(v));
                    break;
                case "qtdCroissant":
                    quantityCroissant.setText(String.valueOf(v));
                    break;
                case "qtdBoloFuba":
                    quantityBoloFuba.setText(String.valueOf(v));
                    break;
                case "qtdBoloFlorestaNegra":
                    quantityDarkForestCake.setText(String.valueOf(v));
                    break;
                case "qtdBoloMorango":
                    quantityStrawberryCake.setText(String.valueOf(v));
                    break;
                default:
                    break;
            }
        });
    }

    private void finalizarPedido(String clientName, Order clientOrder) {
        List<Product> productsList = new ArrayList<>();
        HashMap<String, Integer> productQuantities = new HashMap<>();
        Double totalPrice = 0.0;

        if(Integer.parseInt(quantityPingado.getText().toString()) > 0){
            Product prod = repository.findById(ProductsEnum.PINGADO.getProductId());
            productsList.add(prod);
            productQuantities.put("qtdPingado", Integer.parseInt(String.valueOf(quantityPingado.getText())));
            totalPrice = totalPrice + prod.getProductPrice() * Integer.parseInt(quantityPingado.getText().toString());
        }

        if(Integer.parseInt(quantityCafeComLeite.getText().toString()) > 0){
            Product prod = repository.findById(ProductsEnum.CAFE_LEITE.getProductId());
            productsList.add(prod);
            productQuantities.put("qtdCafeComLeite", Integer.parseInt(String.valueOf(quantityCafeComLeite.getText())));
            totalPrice = totalPrice + prod.getProductPrice() * Integer.parseInt(quantityCafeComLeite.getText().toString());
        }

        if(Integer.parseInt(quantityCafeComLeiteECreme.getText().toString()) > 0){
            Product prod = repository.findById(ProductsEnum.CAFE_LEITE_CREME.getProductId());
            productsList.add(prod);
            productQuantities.put("qtdCafeComLeiteECreme", Integer.parseInt(String.valueOf(quantityCafeComLeiteECreme.getText())));
            totalPrice = totalPrice + prod.getProductPrice() * Integer.parseInt(quantityCafeComLeiteECreme.getText().toString());
        }

        if(Integer.parseInt(quantityCappuccino.getText().toString()) > 0){
            Product prod = repository.findById(ProductsEnum.CAPPUCCINO.getProductId());
            productsList.add(prod);
            productQuantities.put("qtdCappuccino", Integer.parseInt(String.valueOf(quantityCappuccino.getText())));
            totalPrice = totalPrice + prod.getProductPrice() * Integer.parseInt(quantityCappuccino.getText().toString());
        }

        if(Integer.parseInt(quantityEspresso.getText().toString()) > 0){
            Product prod = repository.findById(ProductsEnum.ESPRESSO.getProductId());
            productsList.add(prod);
            productQuantities.put("qtdEspresso", Integer.parseInt(String.valueOf(quantityEspresso.getText())));
            totalPrice = totalPrice + prod.getProductPrice() * Integer.parseInt(quantityEspresso.getText().toString());
        }

        if(Integer.parseInt(quantityCookies.getText().toString()) > 0){
            Product prod = repository.findById(ProductsEnum.COOKIES.getProductId());
            productsList.add(prod);
            productQuantities.put("qtdCookies", Integer.parseInt(String.valueOf(quantityCookies.getText())));
            totalPrice = totalPrice + prod.getProductPrice() * Integer.parseInt(quantityCookies.getText().toString());
        }

        if(Integer.parseInt(quantityCroissant.getText().toString()) > 0){
            Product prod = repository.findById(ProductsEnum.CROISSANT.getProductId());
            productsList.add(prod);
            productQuantities.put("qtdCroissant", Integer.parseInt(String.valueOf(quantityCroissant.getText())));
            totalPrice = totalPrice + prod.getProductPrice() * Integer.parseInt(quantityCroissant.getText().toString());
        }

        if(Integer.parseInt(quantityBoloFuba.getText().toString()) > 0){
            Product prod = repository.findById(ProductsEnum.BOLO_FUBA.getProductId());
            productsList.add(prod);
            productQuantities.put("qtdBoloFuba", Integer.parseInt(String.valueOf(quantityBoloFuba.getText())));
            totalPrice = totalPrice + prod.getProductPrice() * Integer.parseInt(quantityBoloFuba.getText().toString());
        }

        if(Integer.parseInt(quantityDarkForestCake.getText().toString()) > 0){
            Product prod = repository.findById(ProductsEnum.BOLO_FLORESTA_NEGRA.getProductId());
            productsList.add(prod);
            productQuantities.put("qtdBoloFlorestaNegra", Integer.parseInt(String.valueOf(quantityDarkForestCake.getText())));
            totalPrice = totalPrice + prod.getProductPrice() * Integer.parseInt(quantityDarkForestCake.getText().toString());
        }

        if(Integer.parseInt(quantityStrawberryCake.getText().toString()) > 0){
            Product prod = repository.findById(ProductsEnum.BOLO_MORANGO.getProductId());
            productsList.add(prod);
            productQuantities.put("qtdBoloMorango", Integer.parseInt(String.valueOf(quantityStrawberryCake.getText())));
            totalPrice = totalPrice + prod.getProductPrice() * Integer.parseInt(quantityStrawberryCake.getText().toString());
        }

        if(!productsList.isEmpty()){
            clientOrder.setOrderNumber(orderId++);
            clientOrder.setClientName(clientName);
            clientOrder.setOrderDate(LocalDate.now(ZoneId.of("BET")));
            clientOrder.setTotalOrderPrice(totalPrice);
            clientOrder.setProductList(productsList);
            Intent intent = new Intent(OrderScreen.this, OrderConfirmationScreen.class);
            intent.putExtra("clientOrder", clientOrder);
            intent.putExtra("productQuantities", productQuantities);
            startActivity(intent);
        } else {
            AlertDialog alertDialog = new AlertDialog.Builder(OrderScreen.this).create();
            alertDialog.setTitle("Nenhum Produto Selecionado");
            alertDialog.setMessage("É necessário que você escolha pelo menos um produto da lista para poder concluir o pedido");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Fechar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                }
            });
            alertDialog.show();
        }
    }

    private void setButtonsBehavior(String clientName) {
        buttonAddPingado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int actualQuantity = Integer.parseInt(quantityPingado.getText().toString());
                actualQuantity++;
                quantityPingado.setText(String.valueOf(actualQuantity));
            }
        });

        buttonRemovePingado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int actualQuantity = Integer.parseInt(quantityPingado.getText().toString());
                actualQuantity--;
                quantityPingado.setText(String.valueOf(actualQuantity));
                if (actualQuantity < 0) {
                    quantityPingado.setText("0");
                }
            }
        });

        buttonAddCafeLeite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int actualQuantity = Integer.parseInt(quantityCafeComLeite.getText().toString());
                actualQuantity++;
                quantityCafeComLeite.setText(String.valueOf(actualQuantity));
            }
        });

        buttonRemoveCafeLeite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int actualQuantity = Integer.parseInt(quantityCafeComLeite.getText().toString());
                actualQuantity--;
                quantityCafeComLeite.setText(String.valueOf(actualQuantity));
                if (actualQuantity < 0) {
                    quantityCafeComLeite.setText("0");
                }
            }
        });

        buttonAddCafeLeiteCreme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int actualQuantity = Integer.parseInt(quantityCafeComLeiteECreme.getText().toString());
                actualQuantity++;
                quantityCafeComLeiteECreme.setText(String.valueOf(actualQuantity));
            }
        });

        buttonRemoveCafeLeiteCreme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int actualQuantity = Integer.parseInt(quantityCafeComLeiteECreme.getText().toString());
                actualQuantity--;
                quantityCafeComLeiteECreme.setText(String.valueOf(actualQuantity));
                if (actualQuantity < 0) {
                    quantityCafeComLeiteECreme.setText("0");
                }
            }
        });

        buttonAddCappuccino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int actualQuantity = Integer.parseInt(quantityCappuccino.getText().toString());
                actualQuantity++;
                quantityCappuccino.setText(String.valueOf(actualQuantity));
            }
        });

        buttonRemoveCappuccino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int actualQuantity = Integer.parseInt(quantityCappuccino.getText().toString());
                actualQuantity--;
                quantityCappuccino.setText(String.valueOf(actualQuantity));
                if (actualQuantity < 0) {
                    quantityCappuccino.setText("0");
                }
            }
        });

        buttonAddEspresso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int actualQuantity = Integer.parseInt(quantityEspresso.getText().toString());
                actualQuantity++;
                quantityEspresso.setText(String.valueOf(actualQuantity));
            }
        });

        buttonRemoveEspresso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int actualQuantity = Integer.parseInt(quantityEspresso.getText().toString());
                actualQuantity--;
                quantityEspresso.setText(String.valueOf(actualQuantity));
                if (actualQuantity < 0) {
                    quantityEspresso.setText("0");
                }
            }
        });

        buttonAddCookies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int actualQuantity = Integer.parseInt(quantityCookies.getText().toString());
                actualQuantity++;
                quantityCookies.setText(String.valueOf(actualQuantity));
            }
        });

        buttonRemoveCookies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int actualQuantity = Integer.parseInt(quantityCookies.getText().toString());
                actualQuantity--;
                quantityCookies.setText(String.valueOf(actualQuantity));
                if (actualQuantity < 0) {
                    quantityCookies.setText("0");
                }
            }
        });

        buttonAddCroissant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int actualQuantity = Integer.parseInt(quantityCroissant.getText().toString());
                actualQuantity++;
                quantityCroissant.setText(String.valueOf(actualQuantity));
            }
        });

        buttonRemoveCroissant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int actualQuantity = Integer.parseInt(quantityCroissant.getText().toString());
                actualQuantity--;
                quantityCroissant.setText(String.valueOf(actualQuantity));
                if (actualQuantity < 0) {
                    quantityCroissant.setText("0");
                }
            }
        });

        buttonAddBoloFuba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int actualQuantity = Integer.parseInt(quantityBoloFuba.getText().toString());
                actualQuantity++;
                quantityBoloFuba.setText(String.valueOf(actualQuantity));
            }
        });

        buttonRemoveBoloFuba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int actualQuantity = Integer.parseInt(quantityBoloFuba.getText().toString());
                actualQuantity--;
                quantityBoloFuba.setText(String.valueOf(actualQuantity));
                if (actualQuantity < 0) {
                    quantityBoloFuba.setText("0");
                }
            }
        });

        buttonAddDarkForestCake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int actualQuantity = Integer.parseInt(quantityDarkForestCake.getText().toString());
                actualQuantity++;
                quantityDarkForestCake.setText(String.valueOf(actualQuantity));
            }
        });

        buttonRemoveDarkForestCake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int actualQuantity = Integer.parseInt(quantityDarkForestCake.getText().toString());
                actualQuantity--;
                quantityDarkForestCake.setText(String.valueOf(actualQuantity));
                if (actualQuantity < 0) {
                    quantityDarkForestCake.setText("0");
                }
            }
        });

        buttonAddStrawberryCake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int actualQuantity = Integer.parseInt(quantityStrawberryCake.getText().toString());
                actualQuantity++;
                quantityStrawberryCake.setText(String.valueOf(actualQuantity));
            }
        });

        buttonRemoveStrawberryCake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int actualQuantity = Integer.parseInt(quantityStrawberryCake.getText().toString());
                actualQuantity--;
                quantityStrawberryCake.setText(String.valueOf(actualQuantity));
                if (actualQuantity < 0) {
                    quantityStrawberryCake.setText("0");
                }
            }
        });
    }

    //Counters
    private EditText quantityPingado;
    private EditText quantityCafeComLeite;
    private EditText quantityCafeComLeiteECreme;
    private EditText quantityCappuccino;
    private EditText quantityEspresso;
    private EditText quantityCookies;
    private EditText quantityCroissant;
    private EditText quantityBoloFuba;
    private EditText quantityDarkForestCake;
    private EditText quantityStrawberryCake;

    //Buttons
    private Button buttonAddPingado;
    private Button buttonRemovePingado;
    private Button buttonAddCafeLeite;
    private Button buttonRemoveCafeLeite;
    private Button buttonAddCafeLeiteCreme;
    private Button buttonRemoveCafeLeiteCreme;
    private Button buttonAddCappuccino;
    private Button buttonRemoveCappuccino;
    private Button buttonAddEspresso;
    private Button buttonRemoveEspresso;
    private Button buttonAddCookies;
    private Button buttonRemoveCookies;
    private Button buttonAddCroissant;
    private Button buttonRemoveCroissant;
    private Button buttonAddBoloFuba;
    private Button buttonRemoveBoloFuba;
    private Button buttonAddDarkForestCake;
    private Button buttonRemoveDarkForestCake;
    private Button buttonAddStrawberryCake;
    private Button buttonRemoveStrawberryCake;
    private Button buttonSair;
    private Button buttonConfirmarPedido;
}
