const title = document.querySelector('#title');
const content = document.querySelector('#content');
const author = document.querySelector('#author');
const saveBtn = document.querySelector('#btn-save');
const updateBtn =document.querySelector('#btn-update');

function save(){
    const data = {
        title : title.value,
        content : content.value,
        author : author.value
    }
    jQuery.ajax({
        type : 'POST',
        url : '/api/v1/post',
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        data: JSON.stringify(data)
    }).done(()=>{
        alert('글이 등록되었습니다');
        window.location.href = '/';
    }).fail((error)=>{
        alert(JSON.stringify(error));
    });
}

function update(){
    const postId = document.querySelector('#id');

    const data = {
        title : title.value,
        content : content.value
    }
    $.ajax({
        type : 'PUT',
        url : `/api/v1/post/${postId.value}`,
        dataType: 'json',
        contentType : 'application/json; charset=utf-8',
        data: JSON.stringify(data)
    }).done(()=>{
        alert('글이 등록되었습니다');
        window.location.href = '/';
    }).fail((error)=>{
        alert(JSON.stringify(error));
    });
}

saveBtn.addEventListener('click', save);
updateBtn.addEventListener('click', update);
