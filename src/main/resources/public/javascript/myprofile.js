const elements = ['wall', 'album', 'friends']

toggleElement()

function toggleElement(id) {
    if (id === undefined) {
        id = window.location.hash.substring(1);
    }
    if (id === '') {
        id = 'wall'
    }
    id = '_' + id
    for (e of elements) {
        e = '_' + e
        document.getElementById(e).style.display = e == id ? 'block' : 'none'
        document.getElementById(e + 'Button').className = e == id ? "btn btn-primary" : "btn btn-outline-primary"
    }
}