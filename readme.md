# Projecte DI01 – Disseny d’Interfícies  
Aplicació: **ytdlgui (Java Swing)**  
Alumne: **Jordi Gelabert**  
Cicle Formatiu: **DAM – 2n curs**  
Curs: **2025-2026**

Aquest repositori conté totes les entregues de la tasca d’Interfícies (DI), desenvolupades amb Java Swing i organitzades en diferents carpetes, una per cada part de la pràctica.

---

## 📁 Estructura del repositori

### **ytdlgui - tasca 1**  
Primera entrega.  
Creació del projecte Swing, primeres pantalles i estructura inicial de l’aplicació.

### **ytdlgui - tasca 1.2**  
Segona entrega.  
Millora d’interfícies, ús de CardLayout i navegació entre pantalles.

### **ytdlgui - tasca 3.1**  
Creació del component personalitzat **MediaPollingComponent**.  
Implementació del JavaBean sense visuals, propietats i preparació del temporitzador.

### **ytdlgui - tasca 3.2**  
Integració del component dins de la UI.  
Afegir-lo al MainFrame, preparar el flux de login/logout i configurar dependències.

### **ytdlgui - tasca 3.3**  
Implementació completa del component:  
Integració final del component dins l’aplicació ytdlgui.  
Inclou:
- Dependència Maven a mediapolling-component
- Component afegit via NetBeans GUI Builder
- Listener connectat al JTextArea de logs
- Flux complet login → polling → notificació

### **mediapolling-component**
Llibreria Maven independent que implementa el component personalitzat MediaPollingComponent.  
Inclou:
- JavaBean sense visuals (extén JPanel)
- Propietats configurables (apiUrl, pollingIntervalSeconds, running, etc.)
- Polling periòdic amb Timer
- Events i listener MediaPollingListener
- Execució immediata després del login

---

## ▶️ Com executar el projecte

1. Obrir el projecte amb **NetBeans** o qualsevol IDE Java.
2. Assegurar que tens **Java 17** o superior.
3. Compilar primer el projecte `mediapolling-component` (clean & build).
4. Compilar i executar `ytdlgui`.

Executar la classe principal:

gelabert.ytdlgui.Ytdlgui


L’aplicació arrencarà amb el formulari de login i posteriorment carregarà la UI completa.

---

## 📚 Notes addicionals

- Algunes entregues inclouen captures de pantalla i documentació addicional (veure carpeta `docs` si existeix).
- El projecte està desenvolupat amb **Maven** i **Swing**.
- El component `MediaPollingComponent` utilitza una API externa proporcionada a la tasca.

---

## ✔️ Estat de la pràctica

Totes les parts de la tasca **1.1, 1.2, 3.1, 3.2 i 3.3** estan completades i integrades.  
La funcionalitat de polling s’ha encapsulat en una llibreria Maven independent (`mediapolling-component`) i s’ha integrat correctament dins l’aplicació principal mitjançant el Designer de NetBeans.

Cada entrega està reflectida en commits independents dins del repositori.
