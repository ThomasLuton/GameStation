function removeModal(id) {
    const modal = bootstrap.Modal.getInstance('#' + id);
    modal.hide();
}


export default {
    install: (app) => {
        app.config.globalProperties.$modal = {
            remove: (id) => {
                removeModal(id);
            }
        }
    }
};