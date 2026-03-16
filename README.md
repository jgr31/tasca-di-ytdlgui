# Projecte DI -- Disseny d'Interfícies

Aplicació: **ytdlgui (Java Swing)** Alumne: **Jordi Gelabert** Cicle
Formatiu: **DAM -- 2n curs** Curs: **2025-2026**

Aquest repositori conté el desenvolupament del projecte **ytdlgui**, una
aplicació d'escriptori desenvolupada amb **Java Swing** i **Maven** dins
l'assignatura de **Disseny d'Interfícies (DI)**.

El projecte s'ha desenvolupat de manera incremental seguint les
diferents entregues de la pràctica.

------------------------------------------------------------------------

# 📁 Estructura del repositori

    src/
    lib/
    docs/
    pom.xml
    nb-configuration.xml
    README.md

### `src`

Conté el codi font de l'aplicació Java Swing.

### `lib`

Llibreries externes utilitzades pel projecte.

### `docs`

Documentació de la pràctica.

Actualment inclou:

    docs/
     └ DI03_1_JordiGelabert.pdf

### `pom.xml`

Fitxer de configuració Maven amb les dependències i configuració del
projecte.

### `nb-configuration.xml`

Configuració específica del projecte per a NetBeans.

------------------------------------------------------------------------

# ⚙️ Tecnologies utilitzades

-   Java
-   Java Swing
-   Maven
-   NetBeans IDE

------------------------------------------------------------------------

# ▶️ Com executar el projecte

1.  Obrir el projecte amb **NetBeans** o qualsevol IDE compatible amb
    Maven.
2.  Assegurar-se que s'utilitza **Java 17 o superior**.
3.  Executar un **Clean and Build** del projecte.
4.  Executar la classe principal:

```{=html}
<!-- -->
```
    gelabert.ytdlgui.Ytdlgui

L'aplicació iniciarà amb la pantalla de **login** i carregarà
posteriorment la interfície principal.

------------------------------------------------------------------------

# 📦 Component MediaPolling

El projecte utilitza un component personalitzat anomenat
**MediaPollingComponent**, implementat com una llibreria Maven
independent.

Aquest component permet:

-   fer polling periòdic a una API
-   generar notificacions
-   enviar events a la interfície principal

La llibreria es troba en un repositori separat:

    mediapolling-component

------------------------------------------------------------------------

# ✔️ Estat del projecte

El projecte inclou totes les entregues desenvolupades durant la
pràctica:

-   DI01 -- Creació del projecte i primeres pantalles
-   DI03 -- Desenvolupament i integració del component MediaPolling
-   DI04 -- Documentació del projecte

Cada entrega està registrada mitjançant commits dins el repositori.

------------------------------------------------------------------------

# 📄 Documentació

La documentació associada al projecte es troba a la carpeta:

    docs/
