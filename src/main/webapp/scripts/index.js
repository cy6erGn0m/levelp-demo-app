function onSearch() {
    var partId = document.getElementById('searchField').value;
    var url = '/parts/findByPartId?part-id=' + partId;

    var resultsTable = document.getElementById('resultsTable');
    while (resultsTable.hasChildNodes()) {
        resultsTable.removeChild(resultsTable.firstChild)
    }

    var request = new XMLHttpRequest();
    request.open('GET', url, true);
    request.send();

    request.onreadystatechange = function (ev) {
        if (request.readyState != 4) return;

        var result = JSON.parse(request.response).rows;
        if (!result) {
            result = [];
        }

        processSearchResponse(resultsTable, result)
    };
}

function processSearchResponse(resultsTable, result) {
    for (var i = 0; i < result.length; ++i) {
        var part = result[i];

        var row = document.createElement('tr');
        var partIdCell = document.createElement('td');
        var titleCell = document.createElement('td');
        row.appendChild(partIdCell);
        row.appendChild(titleCell);

        partIdCell.textContent = part.partId;
        titleCell.textContent = part.title;

        resultsTable.appendChild(row);
    }
}