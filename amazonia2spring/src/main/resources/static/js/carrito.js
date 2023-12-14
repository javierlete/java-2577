'use strict';

window.addEventListener('DOMContentLoaded', function() {
	const menos = document.querySelectorAll('.menos');
	const mas = document.querySelectorAll('.mas');
	
	for(const boton of menos) {
		boton.addEventListener('click', clickMenos);
	}
	
	mas.forEach(boton => boton.addEventListener('click', clickMas));
});

function clickMas() {
	const botonMas = this;
	
//	console.log(boton);
	
	const div = botonMas.parentNode;
	
//	console.log(div);
	
	const input = div.querySelector('input');
	
//	console.log(input);
	
	input.value++;
	
	const botonMenos = div.querySelector('.menos');
	
	botonMenos.disabled = false; 
}

function clickMenos() {
	const boton = this;
	const input = boton.parentNode.querySelector('input');
	
	input.value--;
	
	if(input.value == 0) {
		boton.disabled = true;
	}
}
