const indexHTML = `
<div class="UnderCoverText custom-under-cover">
    <div>
        See also the <a href="https://github.com/JetBrains/lets-plot-kotlin">GitHub repository</a> of the library.
    </div>
</div>
`;

if (document.readyState == 'loading') {
    document.addEventListener('DOMContentLoaded', onDocumentReady, false);
} else
    onDocumentReady();


function onDocumentReady() {
    const coverElem = document.querySelector("#main div.cover");
    if (coverElem.textContent.trim().split(" ").indexOf("Lets-Plot-Kotlin") == 0) {
        const newElem = htmlToElement(indexHTML);
        coverElem.parentElement.insertBefore(newElem, coverElem.nextSibling);
    }
}

function htmlToElement(html) {
    const template = document.createElement('template');
    template.innerHTML = html.trim();
    return template.content.firstChild;
}