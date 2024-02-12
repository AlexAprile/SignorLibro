<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Registrazione</title>
  <link rel="stylesheet" href="./css/styles.css">
  <script src="script/FormValidationCliente.js"></script>

</head>
<body>

<div class="titolo">
  <img src="./icons/logoVerde.png" alt="SignorLibro logo" title="SignorLibro logo">
</div>

<h1 class="titolo">Inserimento Nuovo Utente:</h1>
<fieldset class="titolo">
  <legend>Form inserimento:</legend>

  <form action="registrazione-controller" method="post">

    <label for="email">e-Mail</label>
    <input type="email" id="email" name="email"
           placeholder="nomecognome@signorlibro.it"
           onkeyup="emailValidation(this.form.email);" required><br><br>

    <label for="c-email">e-Mail</label>
    <input type="email" id="c-email" name="c-email"
           placeholder="nomecognome@signorlibro.it"
           onkeyup="emailValidation(this.form.email);" required><br><br>

    <label for="password">password</label>
    <input type="password" id="password" name="password"
           placeholder="Minimo 4 caratteri"
           onkeyup="passwordValidation(this.form.password);" required><br><br>

    <label for="c-password">password</label>
    <input type="password" id="c-password" name="c-password"
           placeholder="Minimo 4 caratteri"
           onkeyup="passwordValidation(this.form.password);" required><br><br>

    <label for="nome">Nome</label>
    <input type="text" id="nome" name="nome"
    onkeyup="nomeClienteValidation(this.form.nome);" required><br><br>

    <label for="lastName">Cognome</label>
    <input type="text" id="lastName" name="cognome"
    onkeyup="cognomeValidation(this.form.cognome);" required><br><br>

<!--
        manca data di nascita
-->

<!--
    <label for="indirizzo">Indirizzo</label>
    <input type="text" id="indirizzo" name="indirizzo" placeholder="Città/Via"
    onkeyup="indirizzoValidation(this.form.indirizzo);" required><br><br>

    <label for="telefono">Numero di telefono</label>
    <input type="text" id="telefono" name="telefono" title="facoltativo"
           placeholder="es: 1234567890"
    onkeyup="telefonoValidation(this.form.telefono);" required><br><br>

    -->

    <input type="submit" value="Inserisci">

  </form>
</fieldset>
</body>
</html>
