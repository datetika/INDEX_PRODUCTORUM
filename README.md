
# INDEX PRODUCTORUM

![173901578-0cc418dd-eb6d-4a4b-96eb-cfa30a012172](https://user-images.githubusercontent.com/33204630/174042636-463f62c2-3e7c-458b-a546-7942ab92538e.png)
<br />

## DESCRIPCION:
>Aplicacione para hacer la lista de compra. Permite listar de productos que se iran guardando en base de datos locales y remotas.Permite controlar los gastos y agregar promociones.
<br />

### Características

1. Autentificarse
2. Crear, modificar, eliminar leer la lista de compra
4. Compartir la lista con otros usuarios y  valorar  la compra
4. Crear promociones de la  compra
5. Animaciones
 
### TAREAS A HACER
- [X] CREAR UI
- [ ] CREAR UML
- [ ] CODIFICAR
- [ ] TESTEAR

<br />

### Colaboradores

| Miembros del equipo|       GITHUB        |  
| -------------      |:-------------:      | 
| Vazguen            | MrtVazguen          |
| Andrés             | Fahrek              |
<br />
<br />




### Herramientas utilizadas
* SqLite (local)
* Firestore (remoto)
  <br /><br /> 

## Extenciones de Grandle
> Instrucciones de configuración:

### Navigation drawer (menu barra lateral)[:link:](https://material.io/components/navigation-drawer)

### Vinculación de vista [:link:](https://developer.android.com/topic/libraries/view-binding?hl=es-419)
1. Vinculación de vista en build.gradle(:app)

```
 viewBinding {
        enabled = true
    }
```

2. Cómo usar la vinculación de vista en actividades
```
  binding = ResultProfileBinding.inflate(getLayoutInflater());
  View view = binding.getRoot();
  setContentView(view);

```


### Componentes de navegacion [:link:](https://developer.android.google.cn/guide/navigation/navigation-getting-started?hl=es-419)
```
    def nav_version = "2.4.2"

    // Java language implementation
    implementation "androidx.navigation:navigation-fragment:$nav_version"
    implementation "androidx.navigation:navigation-ui:$nav_version"

    // Kotlin
    implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
    implementation "androidx.navigation:navigation-ui-ktx:$nav_version"

    // Feature module Support
    implementation "androidx.navigation:navigation-dynamic-features-fragment:$nav_version"

    // Testing Navigation
    androidTestImplementation "androidx.navigation:navigation-testing:$nav_version"

    //Save args
    implementation "androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version"

    // Jetpack Compose Integration
    //implementation "androidx.navigation:navigation-compose:$nav_version"

```
  
> Cómo agregar un NavHostFragment a través de XML
``` 
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <androidx.appcompat.widget.Toolbar
        .../>

    <androidx.fragment.app.FragmentContainerView
        android:id="@+id/nav_host_fragment"
        android:name="androidx.navigation.fragment.NavHostFragment"
        android:layout_width="0dp"
        android:layout_height="0dp"
        app:layout_constraintLeft_toLeftOf="parent"
        app:layout_constraintRight_toRightOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"

        app:defaultNavHost="true"
        app:navGraph="@navigation/nav_graph" />

    <com.google.android.material.bottomnavigation.BottomNavigationView
        .../>

</androidx.constraintlayout.widget.ConstraintLayout>
```



### Firestore extencions [:link:](https://firebase.google.com/docs/android/setup?hl=es-419)
```
dependencies {
  // ...

  // Import the Firebase BoM
  implementation platform('com.google.firebase:firebase-bom:30.1.0')

  // When using the BoM, you don't specify versions in Firebase library dependencies

  // Declare the dependency for the Firebase SDK for Google Analytics
  implementation 'com.google.firebase:firebase-analytics'

  // Declare the dependencies for any other desired Firebase products
  // For example, declare the dependencies for Firebase Authentication and Cloud Firestore
  implementation 'com.google.firebase:firebase-auth'
  implementation 'com.google.firebase:firebase-firestore'
}

```

### FirebaseUI for Cloud Firestore [:link:](https://firebaseopensource.com/projects/firebase/firebaseui-android/firestore/readme/) && github  [:link:](https://github.com/firebase/firebaseui-android/)
> Notificar  cambios de firestore 
```
  FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference tripsRef = db.collection("trips");
        tripsRef.whereArrayContains("users", currentUser.getEmail()).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    Toast.makeText(TripListActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
                    return;
                }
                for(DocumentChange doc:value.getDocumentChanges()){
                    if (doc.getType()==DocumentChange.Type.ADDED){
                        Map<String,Object> trip = doc.getDocument().getData();
                        String date = (String) trip.get("date");
                        String description = (String) trip.get("description");
                        String image_url = (String) trip.get("img_url");
                        ArrayList users = (ArrayList) trip.get("users");
                        TripInfo newTrip = new TripInfo(image_url, date, description,doc.getDocument().getId(), users);
                        trips.add(newTrip);
                    }else if (doc.getType()==DocumentChange.Type.MODIFIED){
                        Map<String,Object> trip = doc.getDocument().getData();
                        String date = (String) trip.get("date");
                        String description = (String) trip.get("description");
                        String image_url = (String) trip.get("img_url");
                        ArrayList users = (ArrayList) trip.get("users");
                        TripInfo newTrip = new TripInfo(image_url, date, description,doc.getDocument().getId(), users);
                        int currentPosition = 0;
                        for(int i=0;i<trips.size();i++){
                            if (doc.getDocument().getId().equals(trips.get(i).tripID)){
                                currentPosition=i;
                                break;
                            }
                        }
                        trips.set(currentPosition,newTrip);

                    }else if (doc.getType()==DocumentChange.Type.REMOVED){
                        String id=doc.getDocument().getId();
                        int currentPosition=-1;
                        for(int i=0;i<trips.size();i++){
                            if (id.equals(trips.get(i).tripID)){
                                currentPosition=i;
                                break;
                            }
                        }
                        trips.remove(currentPosition);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
```
## UI 
![imagen](https://user-images.githubusercontent.com/33204630/175988029-d7367899-4be3-4967-aa5e-1676cf02d40b.png)


### Extras
([Drive link ](https://docs.google.com/document/d/1r5ElcFDWT98yS-NT08viIMSQooUFfycH5JtQUsLnOFA/edit))
<br />

## Licencia
<a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-sa/4.0/88x31.png" /></a><br />Este proyecto tiene una licencia <a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">Creative Commons Attribution-ShareAlike 4.0 International License</a>.
