<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
  <jsp:attribute name="head">
    <title>Login</title>
    <meta name="description" content="Login to your CVBuilder account and get started." />
  </jsp:attribute>

  <jsp:attribute name="footer">
    <script>
      const registerTabBtn = document.querySelector('a[href="#register"]')
      const registerTab = document.querySelector('#register')
      const loginTab = document.querySelector('#login')
      const loginTabBtn = document.querySelector('a[href="#login"]')
      const registerCompanyTabBtn = document.querySelector('a[href="#register-company"]')
      const registerCompanyTab = document.querySelector('#register-company')
      const registerCandidateTab = document.querySelector('#register-candidate')
      const registerCandidateTabBtn = document.querySelector('a[href="#register-candidate"')

      registerTabBtn.addEventListener('click', function () {
        registerTab.style.display = 'block'
        loginTab.style.display = 'none'
      })
      loginTabBtn.addEventListener('click', function () {
        loginTab.style.display = 'block'
        registerTab.style.display = 'none'
      })
      registerCompanyTabBtn.addEventListener('click', function () {
        this.classList.remove('text-gray-500')
        this.classList.add('active', 'text-blue-600')
        registerCandidateTabBtn.classList.remove('active', 'text-blue-600')
        registerCandidateTab.style.display = 'none'
        registerCompanyTab.style.display = 'block'
      })
      registerCandidateTabBtn.addEventListener('click', function () {
        this.classList.remove('text-gray-500')
        this.classList.add('active', 'text-blue-600')
        registerCompanyTabBtn.classList.remove('active', 'text-blue-600')
        registerCandidateTab.style.display = 'block'
        registerCompanyTab.style.display = 'none'
      })
    </script>
  </jsp:attribute>

  <jsp:body>
    <div>
      <ul class="flex flex-wrap border-b border-gray-200 dark:border-gray-700">
        <li class="mr-2">
          <a href="#login" aria-current="page"
            class="inline-block py-4 px-4 text-sm font-medium text-center text-blue-600 rounded-t-lg active">Login</a>
        </li>
        <li class="mr-2">
          <a href="#register"
            class="inline-block py-4 px-4 text-sm font-medium text-center text-gray-500 rounded-t-lg hover:text-gray-600 hover:bg-gray-50">Register</a>
        </li>
      </ul>
    </div>

    <div id="tabs-content">
      <section class="w-full max-w-xs" id="login">
        <form method="POST" action="/login" class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
          <input name="redirect" type="hidden" value="${param.redirect}" />
          <div class="mb-4">
            <input name="email" type="email" placeholder="Email"
              class="shadow appearance-none border w-full py-2 px-3" />
          </div>
          <div class="mb-4">
            <input name="password" type="password" placeholder="Password"
              class="shadow appearance-none border w-full py-2 px-3" />
          </div>

          <button type="submit" class="px-4 py-1 bg-purple-200 hover:bg-purple-400">Login</button>
        </form>
      </section>

      <div id="register" style="display: none;">
        <ul class="flex flex-wrap border-b border-gray-200 dark:border-gray-700">
          <li class="mr-2">
            <a href="#register-candidate" aria-current="page"
              class="inline-block py-4 px-4 text-sm font-medium text-center text-blue-600 rounded-t-lg active">Candidate</a>
          </li>
          <li class="mr-2">
            <a href="#register-company"
              class="inline-block py-4 px-4 text-sm font-medium text-center text-gray-500 rounded-t-lg hover:text-gray-600 hover:bg-gray-50">Company</a>
          </li>
        </ul>

        <section class="w-full max-w-xs" id="register-candidate">
          <form method="POST" action="/register" class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
            <input name="email" type="email" placeholder="Email" />
            <input name="first_name" type="text" placeholder="First Name" />
            <input name="last_name" type="text" placeholder="Last Name" />
            <input name="phone" type="tel" placeholder="Phone number" />
            <input name="password" type="password" placeholder="Password" />
            <input name="confirm_password" type="password" placeholder="Confirm Password" />

            <input name="resource_type" type="hidden" value="candidate" />

            <button type="submit" class="px-4 py-1 bg-purple-200 hover:bg-purple-400">Register</button>
          </form>
        </section>

        <section class="w-full max-w-xs" id="register-company" style="display: none;">
          <form method="POST" action="/register" class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
            <input name="email" type="email" placeholder="Email" />
            <input name="name" type="text" placeholder="Company Name" />
            <input name="phone" type="tel" placeholder="Phone number" />
            <input name="password" type="password" placeholder="Password" />
            <input name="confirm_password" type="password" placeholder="Confirm Password" />

            <input name="resource_type" type="hidden" value="company" />

            <button type="submit" class="px-4 py-1 bg-purple-200 hover:bg-purple-400">Register</button>
          </form>
        </section>
      </div>
    </div>
  </jsp:body>
</t:layout>