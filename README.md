# Projecte DI -- Disseny d'Interfícies

Aplicació: ytdlgui (Java Swing)\
Alumne: Jordi Gelabert\
Cicle Formatiu: DAM -- 2n curs\
Curs: 2025-2026

------------------------------------------------------------------------

# 📁 Estructura del repositori

## ytdlgui - tasca 1

Creació del projecte Swing i estructura inicial.

## ytdlgui - tasca 1.2

Millora d'interfícies, ús de CardLayout i navegació entre pantalles.

## ytdlgui - tasca 3.1.1

Creació del component personalitzat MediaPollingComponent (JavaBean).

## ytdlgui - tasca 3.1.2

Integració del component dins la UI i configuració del flux
login/logout.

## ytdlgui - tasca 3.1.3

Implementació completa del polling: - Timer funcional - Detecció de nous
fitxers - MediaPollingListener - Execució immediata després del login

## ytdlgui - tasca DI04

Millores d'usabilitat, robustesa i experiència d'usuari.

------------------------------------------------------------------------

# 🚀 DI04 -- Millores implementades

## 🎨 1. Aspecte visual i experiència d'usuari

-   Reorganització del MainPanel per millorar distribució.
-   Afegit JProgressBar per indicar estat de descàrrega.
-   Afegit JLabel d'estat (Ready / Downloading / Done / Error).
-   Tooltips en camps i botons.
-   Comportament coherent MP3 / MP4 / Audio Only.

------------------------------------------------------------------------

## 🧠 2. Affordance i restriccions

-   Botó Download desactivat fins que la URL és vàlida.
-   Validació amb expressió regular per URLs de YouTube.
-   Si s'activa "Audio only", es força MP3.
-   Missatges d'error clars.
-   Botó Login activat només amb email vàlid.

------------------------------------------------------------------------

## ⚙️ 3. Millores tècniques d'usabilitat

-   Ús de SwingWorker per evitar bloqueig de la UI.
-   ProgressBar indeterminada durant operacions.
-   Canvi de cursor a WAIT durant processos.
-   Tecla Enter com a botó per defecte.
-   Controls desactivats mentre hi ha operació en curs.

------------------------------------------------------------------------

## 🛡 4. Gestió global d'errors (CrashLogger)

-   Thread.setDefaultUncaughtExceptionHandler implementat.
-   Registre automàtic en fitxer de log.
-   Missatge amigable a l'usuari.
-   No exposa stacktrace a la interfície.

------------------------------------------------------------------------

## 🧱 Arquitectura i bones pràctiques

-   Separació de responsabilitats:
    -   LoginPanel
    -   MainPanel
    -   MediaPollingComponent
    -   ApiClient
    -   CrashLogger
    -   RememberHelper
-   Arquitectura modular amb Maven.
-   Component JavaBean reutilitzable.
-   Codi organitzat i estructurat.

------------------------------------------------------------------------

# ▶ Execució

## Requisits

-   Java 24
-   Maven
-   NetBeans 28

## Execució

mvn clean install\
mvn exec:java

O executar la classe gelabert.ytdlgui.Ytdlgui des de l'IDE.

------------------------------------------------------------------------

# ✔ Estat final

✔ DI04 complet\
✔ UI millorada\
✔ Operacions asíncrones\
✔ Gestió d'errors global\
✔ Projecte funcional i compilable
