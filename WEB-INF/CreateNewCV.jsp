<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <section>
        <h1>Create CV</h1>
        <form method="POST" action="/CV">
          <input name="title"      type="text" placeholder="Title of CV" />
          <textarea name="experience"   rows="4" cols="50" placeholder="experience"></textarea>
          <textarea name="education" rows="4" cols="50" placeholder="education"> </textarea>  
          <textarea name="about" rows="4" cols="50" placeholder="about"></textarea>
          <button type="submit">CreateCV</button>
        </form>
      </section>
</body>
</html>