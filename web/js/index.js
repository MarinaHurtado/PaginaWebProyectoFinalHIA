/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var items = {
    "Leche": 21,
    "Frijol":10,
    "Cereal":123
};

function cambiaPrecio(nombre){
    var txtPrecio = document.getElementById("precioArticulo");
    var txtPrecio2 = document.getElementById("precioArticulo2");
    for(var nom in items){
        if(nom===nombre){
            txtPrecio.value = items[nombre];
            txtPrecio2.value = items[nombre];
            break;
        }
    }
}

function init(){
    var selectItem = document.querySelector("#nombreArticulo");
    for(var nombre in items){
        var precio = items[nombre];
        var el = document.createElement("option");
        el.textContent = nombre;
        el.value = nombre;
        selectItem.appendChild(el);
        console.log("Nombre: "+nombre+" Precio: "+precio);
    }
    var txtPrecio = document.getElementById("precioArticulo");
    txtPrecio.value = items["Leche"];
    var txtPrecio2 = document.getElementById("precioArticulo2");
    txtPrecio2.value = items["Leche"];
}
