function openModal() {

}

export default {
    install: (app) => {
        app.config.globalProperties.$toast = {
            open: () => {
                openModal()
            }
        }
    }
};