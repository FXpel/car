<style>
  /* TABLE STYLE */
  table {
    border:3px solid #6495ed;
    border-collapse:collapse;
    width:90%;
    margin:auto;
  }
  thead, tfoot {
    background-color:#D0E3FA;
    background-image:url(sky.jpg);
    border:1px solid #6495ed;
  }
  tbody {
    background-color:#FFFFFF;
    border:1px solid #6495ed;
  }
  th {
    font-family:monospace;
    border:1px dotted #6495ed;
    padding:5px;
    background-color:#EFF6FF;
    width:25%;
  }
  td {
    font-family:sans-serif;
    font-size:80%;
    border:1px solid #6495ed;
    padding:5px;
    text-align:left;
  }
  caption {
    font-family:sans-serif;
  }

  /* MENU STYLE */
  #menu, #menu ul /* Liste */{
    padding : 0; /* pas de marge intérieure */
    margin : 0; /* ni extérieure */
    list-style : none; /* on supprime le style par défaut de la liste */
    line-height : 21px; /* on définit une hauteur pour chaque élément */
    text-align : center; /* on centre le texte qui se trouve dans la liste */
  }

  #menu /* Ensemble du menu */{
    margin-left: auto;
    margin-right: auto;
    font-weight : bold; /* on met le texte en gras */
    font-family : Arial; /* on utilise Arial */
    font-size : 12px; /* hauteur du texte : 12 pixels */
  }

  #menu a /* Contenu des listes */{
    display : block; /* on change le type d'élément, les liens deviennent des balises de type block */
    padding : 0; /* aucune marge intérieure */
    background : #D0E3FA; /* couleur de fond */
    color : black; /* couleur du texte */
    text-decoration : none; /* on supprime le style par défaut des liens (la plupart du temps = souligné) */
    width : 250px; /* largeur */
  }

  #menu li /* Elements des listes */{
    float : left;
    border-right : 1px solid #fff; /* on met une bordure blanche à droite de chaque élément */
  }

  #menu li{
    border-right: 1px solid transparent ; /* on met une bordure transparente à droite de chaque élément */
  }

  #menu li ul /* Sous-listes */{
    position: absolute; /* Position absolue */
    width: 144px; /* Largeur des sous-listes */
    left: -999em; /* on envoie loin du champ de vision */
  }

  #menu li ul li /* Éléments de sous-listes */{
    border-top : 1px solid #fff; /* on met une bordure blanche en haut de chaque élément d'une sous liste */
  }

  #menu li ul li{
    border-top : 1px solid transparent; /* on met une bordure transparente en haut de chaque élément */
  }

  #menu li ul ul{
    margin    : -22px 0 0 250px ; /* On décale les sous-sous-listes pour qu'elles ne soient pas au dessus des sous-listes */
    border-left     : 1px solid #fff ; /* Petite bordure à gauche pour ne pas coller ... */
  }

  #menu li ul ul{
    border-left     : 1px solid transparent ; /* on met une bordure transparente sur la gauche de chaque élément */
  }

  #menu li:hover ul ul, #menu li.sfhover ul ul /* Sous-sous-listes lorsque la souris passe sur un élément de liste */{
    left: -999em; /* On expédie les sous-sous-listes hors du champ de vision */
  }

  #menu li:hover ul, #menu li li:hover ul, #menu li.sfhover ul, #menu li li.sfhover ul  /* Sous-listes lorsque la souris passe sur un élément de liste ET sous-sous-lites lorsque la souris passe sur un élément de sous-liste */{
    left: auto; /* Repositionnement normal */
    min-height: 0; /* Corrige un bug sous IE */
  }

  /* DIV BOOK */
  .left{
    float: left;
    border: 0px solid black;
    width: 15%;
    height: 20%;
    background-color: #D0E3FA;
    text-align: center;
    clear: both;
  }

  .right{
    float: right;
    border: 0px solid black;
    background-color: #D0E3FA;
    text-align: center;
  }

  /* FORM : https://www.w3schools.com/howto/howto_css_responsive_form.asp*/
  /* Style inputs, select elements and textareas */
   input[type=text], input[type=number], input[type=email], select, textarea{
     width: 100%;
     padding: 12px;
     border: 1px solid #ccc;
     border-radius: 4px;
     box-sizing: border-box;
     resize: vertical;
   }

   /* Style the label to display next to the inputs */
   label {
     padding: 12px 12px 12px 0;
     display: inline-block;
   }

   /* Style the submit button */
   input[type=submit] {
     background-color: #4CAF50;
     color: white;
     padding: 12px 20px;
     border: none;
     border-radius: 4px;
     cursor: pointer;
     float: right;
   }

   /* Style the container */
   .form {
     border-radius: 5px;
     background-color: #f2f2f2;
     padding: 20px;
   }

   /* Floating column for labels: 25% width */
   .label {
     float: left;
     width: 25%;
     margin-top: 6px;
   }

   /* Floating column for inputs: 75% width */
   .input {
     float: left;
     width: 75%;
     margin-top: 6px;
   }

   /* Clear floats after the columns */
   .row:after {
     content: "";
     display: table;
     clear: both;
   }

   /* Responsive layout - when the screen is less than 600px wide, make the two columns stack on top of each other instead of next to each other */
   @media screen and (max-width: 600px) {
     .label, .input, input[type=submit] {
       width: 100%;
       margin-top: 0;
     }
   }
</style>
