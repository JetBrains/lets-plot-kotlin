const indexHTML = `
<div class="UnderCoverText custom-under-cover">
    <p>Example notebooks:</p>
    <ul>
        <li>
            <p>
                <a href="https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/examples/jupyter-notebooks/quickstart.ipynb">quickstart.ipynb</a>
            </p>
        </li>
        <li>
            <p>
                <a href="https://nbviewer.jupyter.org/github/JetBrains/lets-plot-kotlin/blob/master/docs/guide/user_guide.ipynb">user_guide.ipynb</a>
            </p>
        </li>
    </ul>
</div>
`;

document.addEventListener('DOMContentLoaded', function(){

    const coverElem = document.querySelector("#main div.cover");
    if (coverElem.textContent.trim().split(" ").indexOf("Lets-Plot-Kotlin") == 0) {
        const newElem = htmlToElement(indexHTML);
        coverElem.parentElement.insertBefore(newElem, coverElem.nextSibling);
    }

}, false);

function htmlToElement(html) {
    var template = document.createElement('template');
    html = html.trim();
    template.innerHTML = html;
    return template.content.firstChild;
}