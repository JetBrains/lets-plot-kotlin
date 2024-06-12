const titleText = "Lets-Plot for Kotlin"

if (document.readyState == 'loading') {
    document.addEventListener('DOMContentLoaded', onDocumentReady, false);
} else
    onDocumentReady();

function onDocumentReady() {
    fixLogo();
    fixContentOnIndexPage();
    addTargetBlankToLinks();
}

function fixLogo() {
    Array.from(document.getElementsByClassName("library-name--link")).forEach(function (linkElement) {
        linkElement.textContent = titleText;
        linkElement.setAttribute("href", "https://lets-plot.org/kotlin/index.html");
    });
}

function fixContentOnIndexPage() {
    const underCoverText = `
    <div class="UnderCoverText custom-under-cover">
        <div>
            See also the <a href="https://github.com/JetBrains/lets-plot-kotlin" target="_blank">GitHub repository</a> of the library.
        </div>
    </div>
    `;

    const coverElem = document.querySelector("#main div.cover");
    if (coverElem.textContent.trim().split(" ").indexOf("Lets-Plot-Kotlin") == 0) {
        coverElem.querySelector("h1>span>span").textContent = titleText;
        const newElem = htmlToElement(underCoverText);
        coverElem.parentElement.insertBefore(newElem, coverElem.nextSibling);
    }
}

function htmlToElement(html) {
    const template = document.createElement('template');
    template.innerHTML = html.trim();
    return template.content.firstChild;
}

function addTargetBlankToLinks() {
    document.querySelectorAll('[href*="nbviewer.jupyter.org"]').forEach(function (linkElem) {
        linkElem.setAttribute("target", "_blank");
    });
}