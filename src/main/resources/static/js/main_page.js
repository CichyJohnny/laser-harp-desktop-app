const audioPlayer = document.getElementById('audio-player');
let currentPlayingId = null;

function togglePreview(btn) {
    const id = btn.getAttribute('data-id');
    const url = btn.getAttribute('data-url');
    const sheetContainer = document.getElementById('sheet-' + id);
    const btnSpan = btn.querySelector('span');

    if (currentPlayingId === id) {
        stopBrowserAudio();
        return;
    }

    if (currentPlayingId !== null) {
        stopBrowserAudio();
    }

    sheetContainer.style.display = 'block';

    audioPlayer.src = url;
    audioPlayer.play();

    btn.classList.add('playing');
    btnSpan.innerText = "Stop Preview";

    currentPlayingId = id;
}

function stopBrowserAudio() {
    audioPlayer.pause();
    audioPlayer.currentTime = 0;

    if (currentPlayingId) {
        document.getElementById('sheet-' + currentPlayingId).style.display = 'none';

        const prevBtn = document.getElementById('btn-preview-' + currentPlayingId);
        if (prevBtn) {
            prevBtn.classList.remove('playing');
            prevBtn.querySelector('span').innerText = "▶ Preview";
        }
    }
    currentPlayingId = null;
}

function sendToEsp(id, title) {
    stopBrowserAudio();

    showStatus("Sending: " + title);
    fetch('/api/play?id=' + id, { method: 'POST' })
        .then(r => r.text())
        .then(d => console.log(d))
        .catch(e => showStatus("Error: " + e));
}

function showStatus(msg) {
    const el = document.getElementById('status-bar');
    el.innerText = msg;
    el.style.opacity = "1";
    setTimeout(() => { el.style.opacity = "0"; }, 3000);
}

audioPlayer.onended = function() {
    stopBrowserAudio();
}

function filterTracks() {
    const input = document.getElementById('searchInput');
    const filter = input.value.toLowerCase();

    const cards = document.getElementsByClassName('track-card');

    for (let i = 0; i < cards.length; i++) {
        const card = cards[i];
        const titleElement = card.querySelector('.track-title');

        if (titleElement) {
            const titleText = titleElement.innerText || titleElement.textContent;

            if (titleText.toLowerCase().indexOf(filter) > -1) {
                card.style.display = "";
            } else {
                card.style.display = "none";
            }
        }
    }
}