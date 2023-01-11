function sendRequest(formData, responseType){
    return new Promise((resolve,reject)=>{
        const xhr = new XMLHttpRequest();
        xhr.open('POST',"http://arithmetic.cf/arithmetic/calculate");
        xhr.setRequestHeader('Content-Type',responseType);

        xhr.onload = () => {
            if(xhr.status >= 400){
                reject(xhr.responseText)
            } else{
                resolve(xhr.responseText)
            }
        }

        xhr.onerror = () => {
            reject(xhr.response)
        }
        xhr.send(formData);
    })
}

function dataForm(){
    const inputFileType = document.getElementById("inputfiletype");
    const inputFile = document.getElementById("inputfile");
    const inputZip = document.getElementById("input-zip");
    const inputDec = document.getElementById("input-dec");
    const outputFileType = document.getElementById("outputfiletype");
    const outputZip = document.getElementById("output-zip");
    const outputEnc = document.getElementById("output-enc");

    const formData = new FormData();

    const inputFileTypeOption = inputFileType.getElementsByTagName('option');
    const outputFileTypeOption = outputFileType.getElementsByTagName('option');

    let inputType = '';
    let outputType = '';

    for(let i = 0; i < inputFileTypeOption.length; i++){
        if(inputFileTypeOption[i].selected){
            inputType = inputFileTypeOption[i].value;
        }
    }
    for(let i = 0; i < outputFileTypeOption.length; i++){
        if(outputFileTypeOption[i].selected){
            outputType = outputFileTypeOption[i].value;
        }
    }

    const options = new Blob([JSON.stringify({
        inputType: inputType,
        inputZip:  inputZip,
        inputDec: inputDec,
        outputType: outputType,
        outputZip: outputZip,
        outputEnc: outputEnc
    })],{type: "application/json"});

    formData.append("file",inputFile.files[0]);
    formData.append("options",options);

    let responseType = '';
    let extension = '';
    switch(outputType){
        case "plain":
            responseType = 'text/plain';
            extension = 'txt';
            break;
        case "json":
            responseType = 'application/json';
            extension = 'json';
            break;
        case "xml":
            responseType = 'application/xml';
            extension = 'xml';
    }

    if(outputZip){
        responseType = 'application/zip';
        extension = 'zip';
    }

    sendRequest(formData, responseType).then((responseText) => {
        let uri = 'data:' + responseType + ';base64,' + btoa(responseText);
        let name = 'results.' + extension;

        let link = document.createElement("a");
        link.download = name;
        link.href = uri;
        document.body.appendChild(link);
        link.click();
        document.body.removeChild(link);
    });
}