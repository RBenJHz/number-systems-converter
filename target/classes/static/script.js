//Espera a que el contenido completo del DOM esté cargado
document.addEventListener("DOMContentLoaded", () => {
// Obtiene referencias a los elementos del DOM
    const convertButton = document.getElementById("convertButton"); // Botón para realizar la conversión
    const numberInput = document.getElementById("number"); // Campo de entrada para el número
    const sourceSystemSelect = document.getElementById("sourceSystem"); // Selector para el sistema de origen
    const targetSystemSelect = document.getElementById("targetSystem"); // Selector para el sistema de destino
    const resultContainer = document.getElementById("resultContainer"); // Contenedor para mostrar resultados
    const resultElement = document.getElementById("result"); // Elemento donde se mostrará el resultado
    const copyButton = document.getElementById("copyButton"); // Botón para copiar el resultado
    const clearButton = document.getElementById("clearButton");// Botón para limpiar el formulario

    // Función para convertir el número al hacer clic en el botón de convertir
    convertButton.addEventListener("click", async () => {
        const number = numberInput.value.trim(); // Obtener el número ingresado
        const sourceSystem = sourceSystemSelect.value; // Obtener el sistema de origen seleccionado
        const targetSystem = targetSystemSelect.value; // Obtener el sistema de destino seleccionado

        // Validar que todos los campos estén completados
        if (!number || !sourceSystem || !targetSystem) {
            alert("Por favor, complete todos los campos."); // Mensaje de alerta si faltan campos
            return;  // Salir de la función si hay campos vacíos
        }

        // Realiza la solicitud GET al backend para convertir el número
        try {
            const response = await fetch(`/api/converter/convert?number=${number}&sourceSystem=${sourceSystem}&targetSystem=${targetSystem}`);

            // Verifica si la respuesta es correcta
            if (!response.ok) {
                throw new Error(await response.text()); // Obtiene el mensaje de error del backend
            }

            const convertedNumber = await response.text(); // Obtiene el número convertido de la respuesta
            resultElement.textContent = `El número ${number} (${sourceSystem}) se convierte a ${convertedNumber} (${targetSystem})`; // Muestra el resultado
            resultContainer.style.display = "block"; // Muestra el contenedor de resultados

            // Muestra los botones de copiar y limpiar
            copyButton.style.display = "inline-block";
            clearButton.style.display = "inline-block";

        } catch (error) {
            alert(error.message); // Muestra un mensaje de error si falla la solicitud
        }
    });

    // Función para copiar el resultado al portapapeles
    copyButton.addEventListener('click', () => {
        // Extrae solo el número convertido del texto del resultado
        const convertedNumber = resultElement.textContent.match(/se convierte a (\S+)/)[1]; // Extrae el número convertido

        // Copia el número convertido al portapapeles
        navigator.clipboard.writeText(convertedNumber) // Copiar solo el número convertido
            .then(() => {
                alert('Resultado copiado al portapapeles!'); // Mensaje de confirmación al copiar
                copyButton.style.backgroundColor = 'lightgreen'; // Cambia el color del botón al copiar
                setTimeout(() => copyButton.style.backgroundColor = '', 200); // Restablece el color después de un tiempo
            })
            .catch(err => alert('Error al copiar: ' + err));  // Muestra un error si la copia falla
    });

    // Función para limpiar el formulario
    clearButton.addEventListener('click', () => {
        numberInput.value = ''; // Limpia el campo de número
        sourceSystemSelect.selectedIndex = 0; // Restablece la selección del sistema de origen
        targetSystemSelect.selectedIndex = 0; // Restablece la selección del sistema de destino
        resultContainer.style.display = 'none'; // Oculta el contenedor de resultados
        copyButton.style.display = 'none'; // Oculta el botón de copiar
        clearButton.style.display = 'none'; // Oculta el botón de limpiar
    });

        // Función para abrir el modal de información
        document.getElementById('infoButton').addEventListener('click', function() {
            document.getElementById('infoModal').style.display = 'flex'; // Muestra el modal
        });

        // Función para cerrar el modal
        document.querySelector('.close').addEventListener('click', function() {
            document.getElementById('infoModal').style.display = 'none'; // Oculta el modal al cerrar
        });

        // Cierra el modal si se hace clic fuera de él
        window.onclick = function(event) {
            if (event.target == document.getElementById('infoModal')) {
                document.getElementById('infoModal').style.display = 'none';  // Oculta el modal si se hace clic fuera
            }
        }
});
