<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <jsp:attribute name="head">
    <title>Login</title>
    <meta name="description" content="Login to your CVBuilder account and get started." />
  </jsp:attribute>

  <jsp:attribute name="footer">
  </jsp:attribute>

  <jsp:body>
    <section>
      <h1>Login</h1>
      <form method="POST" action="/login">
        <input name="email"    type="email" placeholder="Email" />
        <input name="password" type="password" placeholder="Password" />
  
        <button type="submit">Login</button>
      </form>
    </section>

    <section>
      <h1>Register as a Candidate</h1>
      <form method="POST" action="/register">
        <input name="email"      type="email" placeholder="Email" />
        <input name="first_name" type="text" placeholder="First Name" />
        <input name="last_name"  type="text" placeholder="Last Name" />
        <input name="phone"      type="tel" placeholder="Phone number" />
        <input name="password"   type="password" placeholder="Password" />
        <input name="confirm_password"  type="password" placeholder="Confirm Password" />

        <input name="resource_type" type="hidden" value="candidate" />
  
        <button type="submit">Register</button>
      </form>
    </section>

    <section>
      <h1>Register as a Company</h1>
      <form method="POST" action="/register">
        <input name="email"    type="email" placeholder="Email" />
        <input name="name"     type="text" placeholder="Company Name" />
        <input name="phone"    type="tel" placeholder="Phone number" />
        <input name="password" type="password" placeholder="Password" />
        <input name="confirm_password"  type="password" placeholder="Confirm Password" />

        <input name="resource_type" type="hidden" value="company" />
  
        <button type="submit">Register</button>
      </form>
    </section>
  </jsp:body>
</t:layout>