# 🎬 DI-U4 · App Películas: importar el componente `Valoracion`

**Módulo:** Desarrollo de Interfaces (2º DAM) · **Unidad:** U4.1 Creación de componentes visuales

Aplicación que **importa** el componente de estrellas desde su repositorio de GitHub,
[DI-U4-Componente-Valoracion](https://github.com/IsmaelAndroid/DI-U4-Componente-Valoracion), a través de JitPack.

Es una cartelera. A diferencia de la [app de recetas](https://github.com/IsmaelAndroid/DI-U4-App-Recetas),
aquí el **mismo componente** se usa de dos formas distintas cambiando solo sus propiedades:

| Uso | Propiedades |
|---|---|
| Media del público | **solo lectura** (sin `onValorCambiado`), `tamano = 16.dp`, `colorActivo` rojo |
| Tu nota | interactiva, `maximo = 10`, `tamano = 24.dp`, `colorActivo` amarillo |

![Captura](recursos/captura.png)

## 🔌 Las 3 líneas que importan el componente

| Fichero | Qué se añade |
|---|---|
| `settings.gradle.kts` | `maven("https://jitpack.io")` en los repositorios |
| `gradle/libs.versions.toml` | `valoracion = { group = "com.github.IsmaelAndroid", name = "DI-U4-Componente-Valoracion", version.ref = "valoracion" }` |
| `app/build.gradle.kts` | `implementation(libs.valoracion)` |

Busca las marcas ⭐ en el código: señalan cada sitio donde aparece el componente importado.

## ▶️ Abrir y ejecutar

1. **Code ▸ Download ZIP**, descomprime en una ruta corta (por ejemplo `C:\DAM\`).
2. Android Studio: **File ▸ Open…** → la carpeta del proyecto → **Trust Project**.
3. Espera a la sincronización: la primera vez Gradle descarga el componente desde JitPack.
4. Ejecuta **app**.

El paso a paso completo está en el PDF de la guía, en el repositorio del componente.
