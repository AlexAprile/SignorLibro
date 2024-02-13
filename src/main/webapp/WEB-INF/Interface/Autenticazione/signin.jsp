<!DOCTYPE html>
<html lang="it">
<head>
    <link rel="icon" href="images/logo(2).gif" type="image/gif">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="./css/signin.css">
</head>
<body>
<div class="container">
    <h1>Login</h1>
    <form id="signin" action="/AutenticazioneController/login" method="post">
        <label for="email">Email</label>
        <input type="email" id="email" name="email" required>
        <label for="password">Password</label>
        <input type="password" id="password" name="psw" required>
        <button type="submit">Accedi</button>
    </form>
    <a href="/registrazione-controller" style="font-size: 15px">registrati</a>
</div>
<script src="library.js"></script>
</body>
</html>
