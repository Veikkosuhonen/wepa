const elements = ['wall', 'album', 'friend']

toggleElement()

function toggleElement(id) {
    if (id === undefined) {
        id = window.location.hash.substring(1);
    }
    if (id === '') {
        id = 'friend'
    }
    for (e of elements) {
        document.getElementById(e).style.display = e == id ? 'block' : 'none'
        document.getElementById(e + 'Button').className = e == id ? "btn btn-primary" : "btn btn-outline-primary"
    }
}