function setCookie(){
    let user = document.querySelector('#username').value
    let pass = document.querySelector('#password').value

    document.cookie = `myusername=${user};path=http://localhost/web6pm`
    document.cookie = `myupassword=${pass};path=http://localhost/web6pm`
}

function getCookieData() {
    console.log(document.cookie);
    let user = getCookie('myusername')
    let password = getCookie('myupassword')

    document.querySelector('#username').value = user
    document.querySelector('#password').value = password
}

function getCookie(cname) {
    let name = `${cname}=`;
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for (let c of ca) {
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}


