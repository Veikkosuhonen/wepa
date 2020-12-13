function toggleComments(id) {
    var comments = document.getElementById("comments" + id)
    if (comments.style.display === "none") {
        comments.style.display = "block";
    } else {
        comments.style.display = "none";
    }
}

function toggleActions(id) {
    var actions = document.getElementById("actions" + id)
    if (actions.style.display === "none") {
        actions.style.display = "block";
    } else {
        actions.style.display = "none";
    }
}

function removeButton(id) {
    var removeConfirm = document.getElementById("removeConfirm")
    removeConfirm.style.display = "block"
    setTimeout(
        function() {
            removeConfirm.style.display = "none"
        }, 5000
    )
}