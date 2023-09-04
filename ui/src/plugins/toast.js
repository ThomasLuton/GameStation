/*
 * Une fonction pour rendre le plugin plus lisible, contient
 * le code de manipulation du composant Bootstrap toast.
 * A noter que la fonction n'est pas exportee, uniquement le plugin.
*/
function showToast(id, cls, message) {
    const container = document.querySelector(`#${id}>.toast`);
    const classes = container.classList;
    classes.add(cls);
    const body = container.querySelector('.toast-body');
    body.textContent = message;
    container.addEventListener('hidden.bs.toast', () => {
        classes.remove(cls);
        body.textContent = '';
    }, { once: true });
    const toast = bootstrap.Toast.getOrCreateInstance(container);
    toast.show();
}

/*
 * Plugin qui enregistre de maniere globale le toast pour y acceder plus facilement
 * dans les composants.
 * Expose deux fonctions pour afficher le toast, soit en "success" soit en "error".
*/
export default {
    install: (app) => {
        app.config.globalProperties.$toast = {
            success: (id, msg) => {
                showToast(id, 'text-bg-success', msg);
            },
            error: (id, msg) => {
                showToast(id, 'text-bg-danger', msg);
            }
        }
    }
};