function toggleComments(id) {
    var comments = document.getElementById("comments" + id)
    if (comments.style.display === "none") {
        comments.style.display = "block";
    } else {
        comments.style.display = "none";
    }
}