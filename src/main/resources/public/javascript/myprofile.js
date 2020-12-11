const elements = ['wall', 'album', 'friends']

toggleElement()

function toggleElement(id) {
    if (id === undefined) {
        id = window.location.hash.substring(1);
    }
    if (id == null || !elements.includes(id)) {
        id = 'wall'
    }
    for (e of elements) {
        document.getElementById(e).style.display = 'none'
        document.getElementById(e + 'Button').className = "btn btn-outline-primary"
    }
    document.getElementById(id).style.display = 'block'
    document.getElementById(id + 'Button').className = "btn btn-primary"
}