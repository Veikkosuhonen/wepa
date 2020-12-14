function updateTitle() {
    var newText = document.getElementById('title-input').value
    if (newText === '') newText = 'Fakebook';
    document.getElementById('title').innerHTML = newText
    document.getElementById('tab-title').innerHTML = newText
}