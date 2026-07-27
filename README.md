# Analizador de Recursos

Aplicación de escritorio en Java (Swing) que analiza un recurso web a partir de su URL y muestra su tipo, tamaño y fecha de última modificación.

## Características

- Interfaz gráfica con un campo de texto para introducir la URL del recurso a analizar.
- Analiza el recurso en un hilo independiente (`Analizador extends Thread`) para no bloquear la interfaz.
- Obtiene mediante `URLConnection` el tipo de contenido (`Content-Type`), el tamaño en KB y la fecha de última modificación del recurso.
- Clasifica automáticamente el recurso en Audio, Imagen, Vídeo, PDF, Web u Otros, marcando el `JRadioButton` correspondiente según la cabecera `Content-Type`.
- Manejo de errores de URL mal formada o de lectura/escritura, mostrando el fallo en la propia interfaz de resultado.
- Botón "Volver" para regresar a la ventana inicial y analizar un nuevo recurso.

## Tecnologías

- Java (Swing / AWT para la interfaz gráfica)
- Apache Ant + NetBeans (`build.xml`, estructura de proyecto NetBeans)

## Instalación / Cómo ejecutarlo

**Opción rápida (ejecutable ya compilado):**
```
java -jar dist/Analizador_Recursos.jar
```

**Compilando desde el código fuente:**
1. Abre el proyecto con NetBeans (o cualquier IDE compatible con Ant), o compílalo manualmente con `ant` desde la raíz del proyecto usando el `build.xml` incluido.
2. Ejecuta la clase `Ventana_Interfaz` (contiene el `main`).
3. Introduce una URL en el campo de texto y pulsa "Analizar".

Requiere Java 7 o superior.

Ejercicio académico que practica el uso de hilos (`Thread`), la clase `URLConnection` para inspeccionar cabeceras HTTP, y la construcción de interfaces gráficas con Swing.

## Licencia

GPL versión 3 (ver archivo [LICENSE](LICENSE)).
