------------------------------------------------------------------------------------------------------------------------------------
SISTEMA DE GESTIÓN DE PRESENTACIONES
------------------------------------------------------------------------------------------------------------------------------------

Este programa en Java permite gestionar presentaciones en diferentes días de la semana, añadiendo, mostrando, recalendarizando 
y modificando asistentes de presentaciones. Utiliza un HashMap donde las presentaciones se agrupan por día y están asociadas a 
participantes que pueden ser expositores o asistentes.

|| Funciones ||

- Registrar presentación: Permite agregar una nueva presentación para un día de la semana con su título, hora, sala y lista de 
participantes.

- Mostrar información de presentación: Muestra los detalles de una presentación específica, incluidos los participantes.

- Recalendarizar presentación: Mueve una presentación a otro día de la semana.

- Cambiar asistente de presentación: Modifica los detalles de los asistentes o expositores de una presentación.

|| Cómo Usar ||

- Menú Principal:

Al ejecutar el programa, se te presentará un menú con 5 opciones:

1. Registrar presentación.
2. Mostrar información de presentación.
3. Recalendarizar presentación.
4. Cambiar asistente de presentación.
5. Salir del programa.
________________________________________________________________________________________________________________________________
1. Registrar Presentación:

Elige un día de la semana.

Ingresa los datos solicitados: título, hora de inicio y fin, sala y lista de participantes (nombre, rut y si es expositor o asistente).
________________________________________________________________________________________________________________________________
2. Mostrar Información de Presentación:

Selecciona un día y el programa mostrará los detalles de las presentaciones registradas en ese día.
________________________________________________________________________________________________________________________________
3. Recalendarizar Presentación:

Elige la presentación que deseas mover y selecciona el nuevo día en el que quieres que ocurra.
________________________________________________________________________________________________________________________________
4. Cambiar Asistente de Presentación:

Modifica la información de un asistente o expositor en una presentación seleccionada.
________________________________________________________________________________________________________________________________
5. Salir

Usalo para salir del programa
________________________________________________________________________________________________________________________________

|| Clases Principales ||

- Main
  Contiene el menú principal y la lógica de interacción con el usuario. Gestiona las opciones del menú y llama a los métodos 
  necesarios para registrar, mostrar, recalendarizar o modificar presentaciones.

- Persona
  Representa a una persona que puede ser un asistente o expositor en una presentación. Los atributos incluyen:

  nombre: Nombre de la persona.
  rut: RUT (identificador) de la persona.
  esExpositor: Indica si es expositor (true) o asistente (false).

- Presentacion
  Modela una presentación con atributos como:

  titulo: Título de la presentación.
  horaInicio: Hora de inicio.
  horaFin: Hora de fin.
  sala: Sala donde se llevará a cabo.
  listaParticipante: Lista de participantes (instancias de la clase Persona).

  Métodos destacados:

  addParticipant: Agrega participantes a la presentación.
  modifyParticipant: Modifica la información de un participante.
  removeParticipant: Elimina un participante de la lista.
  mostrarInformacion: Muestra la información completa de la presentación.
